package com.example.my_child.presentation.teacher.absent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.absent.AbsentAdapter
import com.example.my_child.presentation.parent.absent.AbsentViewModel
import com.example.my_child.presentation.parent.absent.AbsentViewModelFactory
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.utils.Constants
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.fragment_absent.*

class TeacherAbsentFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            TeacherAbsentFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val TeacherAbsentFragment_TAG = "TeacherAbsentFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_absent, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, AbsentViewModelFactory())
            .get(AbsentViewModel::class.java)

        add_absent_layout.setupVisibility(false)
        add_absent.setupVisibility(false)
        initList(viewModel)
    }

    private fun initList(viewModel: AbsentViewModel) {
        disposable.add(
            viewModel.getAbsent(teacherId, childId)
                .subscribe({
                    absent_history.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = AbsentAdapter(requireContext(), it)
                    }
                }, Throwable::printStackTrace)
        )
    }

    override fun onResume() {
        super.onResume()
        (activity as TeacherHomeActivity).setTitle("Отсутствия")
    }
}