package com.example.my_child.presentation.teacher.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants.USER_ID
import com.example.my_child.utils.observeNotNullOnce
import com.example.my_child.utils.observeOnce
import kotlinx.android.synthetic.main.fragment_homework.*

class TeacherHomeworkFragment : BaseFragment() {

    companion object {
        fun newInstance(userId: Int): Fragment =
            TeacherHomeworkFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }

        const val TeacherHomeworkFragment_TAG = "TeacherHomeworkFragment_TAG"
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

        add_homework.setOnClickListener { addHomework() }
        subscribeBottomSheetDialogFragment(viewModel)
        initList(viewModel)
    }

    private fun subscribeBottomSheetDialogFragment(viewModel: HomeworkViewModel) {
        viewModel
            .getHomework()
            .observeNotNullOnce(viewLifecycleOwner) {
                disposable.add(
                    viewModel
                        .addHomework(it)
                        .subscribe({
                            initList(viewModel)
                        }, Throwable::printStackTrace)
                )
            }
    }

    private fun addHomework() {
        AddHomeworkBottomSheetDialog
            .newInstance(userId)
            .show(childFragmentManager, AddHomeworkBottomSheetDialog.TAG)
    }

    private fun initList(viewModel: HomeworkViewModel) {
        disposable.add(
            viewModel
                .getHomework(userId)
                .subscribe({
                    homework_list.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = HomeworkAdapter(requireContext(), it)
                    }
                }, Throwable::printStackTrace)
        )
    }
}
