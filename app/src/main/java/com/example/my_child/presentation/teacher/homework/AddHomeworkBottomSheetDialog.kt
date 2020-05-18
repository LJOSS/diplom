package com.example.my_child.presentation.teacher.homework

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.HomeworkData
import com.example.my_child.utils.Constants.USER_ID
import com.example.my_child.utils.debugLog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_homework_bottom_sheet_dialog.*

class AddHomeworkBottomSheetDialog : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(idTeacher: Int): BottomSheetDialogFragment =
            AddHomeworkBottomSheetDialog().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, idTeacher)
                }
            }

        const val TAG = "AddHomeworkBottomSheetDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.add_homework_bottom_sheet_dialog, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(parentFragment!!, HomeworkViewModelFactory())
            .get(HomeworkViewModel::class.java)

        save_homework.setOnClickListener {
            if (subject_description.text.toString().isNotEmpty() ||
                subject_name.text.toString().isNotEmpty()
            ) {
                viewModel.postHomework(
                    HomeworkData(
                        requireArguments().getInt(USER_ID),
                        subject_description.text.toString(),
                        subject_description.text.toString(),
                        viewModel.getDate()
                    )
                )
                dismiss()
            }
        }
    }
}