package com.example.my_child.presentation.parent.medicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants

class MedicineFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int): Fragment =
            MedicineFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
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

    }
}