package com.example.my_child.presentation.teacher.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.HomeworkData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants.USER_ID
import com.example.my_child.utils.hideKeyboardNotAlways
import com.example.my_child.utils.setupVisibility
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
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

        val bsb = BottomSheetBehavior.from(add_homework_layout)
        initBottomSheetBehaviour(bsb)
        add_homework.setOnClickListener { bsb.state = BottomSheetBehavior.STATE_EXPANDED }
        save_homework.setOnClickListener {
            sendHomework(bsb, viewModel)
        }
        initList(viewModel)
    }

    private fun sendHomework(
        bsb: BottomSheetBehavior<LinearLayout>,
        viewModel: HomeworkViewModel
    ) {
        when {
            subject_name.text.isEmpty() -> {
                subject_name_layout.error = requireActivity().getString(R.string.empty_field)
            }
            subject_description.text.isEmpty() -> {
                subject_description_layout.error = requireActivity().getString(R.string.empty_field)
            }
            else -> {
                disposable.add(
                    viewModel
                        .addHomework(
                            HomeworkData(
                                userId,
                                subject_name.text.toString(),
                                subject_description.text.toString(),
                                viewModel.getDate()
                            )
                        )
                        .subscribe({
                            initList(viewModel)
                            bsb.state = BottomSheetBehavior.STATE_HIDDEN
                        }, Throwable::printStackTrace)
                )
            }
        }
    }

    private fun initBottomSheetBehaviour(bsb: BottomSheetBehavior<LinearLayout>) {
        bsb.isHideable = true
        bsb.state = BottomSheetBehavior.STATE_HIDDEN
        bsb.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(
                bottomSheet: View,
                newState: Int
            ) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    add_homework.setupVisibility(true)
                    clearHomework()
                    hideKeyboardNotAlways(requireActivity())
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    add_homework.setupVisibility(false)
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        })
    }


    private fun clearHomework() {
        subject_name.text.clear()
        subject_description.text.clear()
        subject_name.clearFocus()
        subject_description.clearFocus()
    }

    private fun initList(viewModel: HomeworkViewModel) {
        disposable.add(
            viewModel
                .getHomework(userId)
                .subscribe({
                    homework_list.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = HomeworkAdapter(requireContext(), it.reversed())
                    }
                }, Throwable::printStackTrace)
        )
    }
}
