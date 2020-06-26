package com.example.my_child.presentation.parent.absent

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.AbsentDataResponse
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.utils.Constants
import com.example.my_child.utils.debugLog
import com.example.my_child.utils.hideKeyboardNotAlways
import com.example.my_child.utils.setupVisibility
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_absent.*
import java.lang.Exception
import java.util.*

class ParentAbsentFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            ParentAbsentFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val ParentAbsentFragment_TAG = "ParentAbsentFragment_TAG"
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
        val bsb = BottomSheetBehavior.from(add_absent_layout)
        initBottomSheetBehaviour(bsb, viewModel)
        add_absent.setOnClickListener { bsb.state = BottomSheetBehavior.STATE_EXPANDED }
        save_absent.setOnClickListener {
            sendAbsent(bsb, viewModel)
        }
        initList(viewModel)
        date.text = viewModel.getDate()
        date.setOnClickListener { initDatePicker(viewModel) }
    }

    private fun initDatePicker(viewModel: AbsentViewModel) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            0,
            { _, year: Int, month: Int, day: Int ->
                date.text = viewModel.convertDate(year, (month + 1), day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
            show()
        }
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

    private fun sendAbsent(bsb: BottomSheetBehavior<LinearLayout>, viewModel: AbsentViewModel) {
        if (reason.text.isEmpty()) {
            reason.error = "Заполните причину отсутсвия"
        } else {
            disposable.add(
                viewModel.insertAbsent(
                    AbsentDataResponse(
                        childId = childId,
                        teacherId = teacherId,
                        message = reason.text.toString(),
                        notes = parent_notes.text.toString(),
                        date = date.text.toString()
                    )
                ).subscribe({
                    bsb.state = BottomSheetBehavior.STATE_HIDDEN
                }, Throwable::printStackTrace)
            )
        }
    }

    private fun initBottomSheetBehaviour(
        bsb: BottomSheetBehavior<LinearLayout>,
        viewModel: AbsentViewModel
    ) {
        bsb.isHideable = true
        bsb.state = BottomSheetBehavior.STATE_HIDDEN
        bsb.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(
                bottomSheet: View,
                newState: Int
            ) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    add_absent.setupVisibility(true)
                    clearAbsent(viewModel)
                    hideKeyboardNotAlways(requireActivity())
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    add_absent.setupVisibility(false)
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        })
    }

    private fun clearAbsent(viewModel: AbsentViewModel) {
        try {
            date.text = viewModel.getDate()
            reason.text.clear()
            parent_notes.text.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as ParentHomeActivity).setTitle("Отсутствия")
    }
}