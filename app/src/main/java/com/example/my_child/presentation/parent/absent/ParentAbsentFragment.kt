package com.example.my_child.presentation.parent.absent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_absent.*

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
        initBottomSheetBehaviour(bsb)
        add_absent.setOnClickListener { bsb.state = BottomSheetBehavior.STATE_EXPANDED }
        save_absent.setOnClickListener {
            sendAbsent(bsb, viewModel)
        }
        initList(viewModel)
    }

    private fun initList(viewModel: AbsentViewModel) {

    }

    private fun sendAbsent(bsb: BottomSheetBehavior<LinearLayout>, viewModel: AbsentViewModel) {

    }

    private fun initBottomSheetBehaviour(bsb: BottomSheetBehavior<LinearLayout>) {

    }
}