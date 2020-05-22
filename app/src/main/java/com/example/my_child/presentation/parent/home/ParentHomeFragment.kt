package com.example.my_child.presentation.parent.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.presentation.parent.date.SelectDateFragment
import com.example.my_child.presentation.parent.date.SelectDateFragment.Companion.SelectDateFragment_TAG
import com.example.my_child.presentation.parent.homework.ParentHomeworkFragment
import com.example.my_child.utils.Constants.USER_ID
import kotlinx.android.synthetic.main.fragment_home.*

class ParentHomeFragment : BaseFragment() {

    companion object {
        fun newInstance(userId: Int): Fragment =
            ParentHomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }

        const val ParentHomeFragment_TAG = "ParentHomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity(), BaseViewModelFactory())
            .get(BaseViewModel::class.java)
        initClickers(viewModel)
    }

    private fun initClickers(viewModel: BaseViewModel) {
        diary_layout.setOnClickListener {
            openFragment(
                SelectDateFragment.newInstance(viewModel.getTeacherId(), userId),
                SelectDateFragment_TAG
            )
        }
        chat_layout.setOnClickListener {
            (activity as ParentHomeActivity).openChat(viewModel.getTeacherId())
        }
        photo_layout.setOnClickListener {}
        homework_layout.setOnClickListener {
            openFragment(
                ParentHomeworkFragment.newInstance(viewModel.getTeacherId()),
                ParentHomeworkFragment.ParentHomeworkFragment_TAG
            )
        }
        medicine_layout.setOnClickListener {
            (activity as ParentHomeActivity).openMedicine(viewModel.getTeacherId())
        }
        absent_track_layout.setOnClickListener {}
        pickup_layout.setOnClickListener { (activity as ParentHomeActivity).openPickup(viewModel.getTeacherId()) }
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        (activity as ParentHomeActivity).openFragmentFromActivity(fragment, tag)
    }
}