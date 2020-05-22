package com.example.my_child.presentation.teacher.pickup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.pickup.PickupAdapter
import com.example.my_child.presentation.parent.pickup.PickupViewModel
import com.example.my_child.presentation.parent.pickup.PickupViewModelFactory
import com.example.my_child.utils.Constants
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.fragment_pickup.*

class TeacherPickupFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            TeacherPickupFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val TeacherPickupFragment_TAG = "TeacherPickupFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_pickup, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, PickupViewModelFactory())
            .get(PickupViewModel::class.java)
        add_pickup_layout.setupVisibility(false)
        add_pickup.setupVisibility(false)
        initList(viewModel)
    }

    private fun initList(viewModel: PickupViewModel) {
        disposable.add(
            viewModel.getPickup(teacherId, childId)
                .subscribe({
                    pickup_history.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = PickupAdapter(requireContext(), it)
                    }
                }, Throwable::printStackTrace)
        )
    }
}