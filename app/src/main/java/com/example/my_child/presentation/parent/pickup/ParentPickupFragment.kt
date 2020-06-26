package com.example.my_child.presentation.parent.pickup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.PickupData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import com.example.my_child.utils.hideKeyboardNotAlways
import com.example.my_child.utils.setupVisibility
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_pickup.*
import java.lang.Exception

class ParentPickupFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            ParentPickupFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                    putInt(CHILD_ID, childId)
                }
            }

        const val ParentPickupFragment_TAG = "ParentPickupFragment_TAG"
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
        val bsb = BottomSheetBehavior.from(add_pickup_layout)
        initBottomSheetBehaviour(bsb)
        add_pickup.setOnClickListener { bsb.state = BottomSheetBehavior.STATE_EXPANDED }
        save_pickup.setOnClickListener {
            sendPickup(bsb, viewModel)
        }
        initList(viewModel)
    }

    private fun sendPickup(bsb: BottomSheetBehavior<LinearLayout>, viewModel: PickupViewModel) {
        if (parent_name.text.isEmpty()) {
            parent_name.error = "Заполните"
        } else {
            disposable.add(
                viewModel.addPickup(
                    PickupData(
                        childId,
                        teacherId,
                        parent_name.text.toString(),
                        relation.text.toString(),
                        car_number.text.toString(),
                        phone_number.text.toString(),
                        parent_notes_qwe.text.toString(),
                        System.currentTimeMillis()
                    )
                ).subscribe({
                    Toast.makeText(requireContext(), "Отправлено", Toast.LENGTH_LONG).show()
                    bsb.state = BottomSheetBehavior.STATE_HIDDEN
                }, Throwable::printStackTrace)
            )
        }
    }

    private fun initList(viewModel: PickupViewModel) {
        disposable.add(
            viewModel
                .getPickup(teacherId, childId)
                .subscribe({
                    pickup_history.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = PickupAdapter(requireContext(), it)
                    }
                }, Throwable::printStackTrace)
        )
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
                    add_pickup.setupVisibility(true)
                    clearPickup()
                    hideKeyboardNotAlways(requireActivity())
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    add_pickup.setupVisibility(false)
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        })
    }

    private fun clearPickup() {
        try {
            parent_name.text.clear()
            relation.text.clear()
            car_number.text.clear()
            phone_number.text.clear()
            parent_notes_qwe.text.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as ParentHomeActivity).setTitle("Увоз")
    }
}