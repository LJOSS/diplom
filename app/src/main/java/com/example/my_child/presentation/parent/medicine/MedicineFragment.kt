package com.example.my_child.presentation.parent.medicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.TEACHER_ID
import com.example.my_child.utils.Constants.USER_ID
import com.example.my_child.utils.hideKeyboardNotAlways
import com.example.my_child.utils.setupVisibility
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_medicine.*

class MedicineFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, userId: Int): Fragment =
            MedicineFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                    putInt(USER_ID, userId)
                }
            }

        const val MedicineFragment_TAG = "MedicineFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_medicine, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, MedicineViewModelFactory())
            .get(MedicineViewModel::class.java)
        val bsb = BottomSheetBehavior.from(add_medicine_layout)
        initBottomSheetBehaviour(bsb)
        add_medicine.setOnClickListener { bsb.state = BottomSheetBehavior.STATE_EXPANDED }
        save_medicine.setOnClickListener {
            sendHomework(bsb, viewModel)
        }
        initList(viewModel)
    }

    private fun initBottomSheetBehaviour(bsb: BottomSheetBehavior<LinearLayout>) {
        bsb.isHideable = true
        bsb.state = BottomSheetBehavior.STATE_HIDDEN
        bsb.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(
                bottomSheet: View,
                newState: Int
            ) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    add_medicine.setupVisibility(true)
                    clearMedicine()
                    hideKeyboardNotAlways(requireActivity())
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    add_medicine.setupVisibility(false)
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        })
    }

    private fun clearMedicine() {
        medicine_name.text.clear()
        frequency.text.clear()
        dosage.text.clear()
        parent_notes.text.clear()
    }

    private fun sendHomework(
        bsb: BottomSheetBehavior<LinearLayout>,
        viewModel: MedicineViewModel
    ) {
        when {
            medicine_name.text.isEmpty() -> {
                medicine_name.error = "Введите название"
                return
            }
            frequency.text.isEmpty() -> {
                frequency.error = "Введите частоту"
                return
            }
            dosage.text.isEmpty() -> {
                dosage.error = "Введите дозировку"
                return
            }
            else -> {
                disposable.add(
                    viewModel.insertMedicine(
                        MedicineData(
                            0,
                            teacherId,
                            userId,
                            System.currentTimeMillis(),
                            medicine_name.text.toString(),
                            frequency.text.toString(),
                            dosage.text.toString(),
                            parent_notes.text.toString()
                        )
                    ).subscribe({
                        bsb.state = BottomSheetBehavior.STATE_HIDDEN
                    }, Throwable::printStackTrace)
                )
            }
        }
    }

    private fun initList(viewModel: MedicineViewModel) {
        disposable.add(
            viewModel.getMedicine(teacherId, userId)
                .subscribe({
                    medicine_history.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = MedicineAdapter(requireContext(), it.reversed(), true) {

                        }
                    }
                }, Throwable::printStackTrace)
        )
    }
}