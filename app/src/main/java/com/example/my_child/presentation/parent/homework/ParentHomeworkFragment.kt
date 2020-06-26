package com.example.my_child.presentation.parent.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.presentation.teacher.homework.HomeworkAdapter
import com.example.my_child.presentation.teacher.homework.HomeworkViewModel
import com.example.my_child.presentation.teacher.homework.HomeworkViewModelFactory
import com.example.my_child.utils.Constants.TEACHER_ID
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.fragment_homework.*

class ParentHomeworkFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int): Fragment =
            ParentHomeworkFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                }
            }

        const val ParentHomeworkFragment_TAG = "ParentHomeworkFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_homework, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, HomeworkViewModelFactory())
            .get(HomeworkViewModel::class.java)

        add_homework_layout.visibility = View.GONE
        add_homework.setupVisibility(false)
        initList(viewModel)
    }

    private fun initList(viewModel: HomeworkViewModel) {
        disposable.add(
            viewModel
                .getHomework(teacherId)
                .subscribe({
                    homework_list.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = HomeworkAdapter(requireContext(), it)
                    }
                }, Throwable::printStackTrace)
        )
    }

    override fun onResume() {
        super.onResume()
        (activity as ParentHomeActivity).setTitle("Домашняя работа")
    }
}