package com.example.my_child.presentation.teacher.medicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DayMedicine
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.medicine.MedicineDayAdapter
import com.example.my_child.presentation.parent.medicine.MedicineViewModel
import com.example.my_child.presentation.parent.medicine.MedicineViewModelFactory
import com.example.my_child.utils.Constants
import com.example.my_child.utils.getFormattedDate
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.fragment_medicine.*

class TeacherMedicineFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            TeacherMedicineFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val TeacherMedicineFragment_TAG = "TeacherMedicineFragment_TAG"
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

        add_medicine.setupVisibility(false)
        add_medicine_layout.setupVisibility(false)

        initList(viewModel)
    }

    private fun initList(viewModel: MedicineViewModel) {
        disposable.add(
            viewModel.getMedicine(teacherId, childId)
                .subscribe({
                    medicine_history.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = MedicineDayAdapter(
                            requireContext(),
                            createListByDay(it.reversed()),
                            false
                        ) {
                            sendAdministeredMedicine(viewModel, it)
                        }
                    }
                }, Throwable::printStackTrace)
        )
    }

    private fun sendAdministeredMedicine(viewModel: MedicineViewModel, it: MedicineData) {
        disposable.add(
            viewModel.administered(it.id)
                .subscribe({},Throwable::printStackTrace)
        )
    }

    private fun createListByDay(list: List<MedicineData>): List<DayMedicine> {
        val medicineByDay = arrayListOf<DayMedicine>()
        list.forEach {
            if (medicineByDay.isEmpty()) {
                medicineByDay.add(DayMedicine(getFormattedDate(it.date), arrayListOf(it)))
            } else {
                val last = medicineByDay.last()
                if (last.date.equals(getFormattedDate(it.date))) {
                    last.list.add(it)
                } else {
                    medicineByDay.add(DayMedicine(getFormattedDate(it.date), arrayListOf(it)))
                }
            }
        }
        return medicineByDay
    }
}