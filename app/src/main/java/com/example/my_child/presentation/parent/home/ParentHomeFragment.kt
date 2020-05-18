package com.example.my_child.presentation.parent.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_child.R
import kotlinx.android.synthetic.main.fragment_home.*

class ParentHomeFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment =
            ParentHomeFragment()

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
        initClickers()
    }

    private fun initClickers() {
        diary_layout.setOnClickListener { }
        chat_layout.setOnClickListener {}
        photo_layout.setOnClickListener {}
        homework_layout.setOnClickListener {}
        medicine_layout.setOnClickListener {}
        absent_track_layout.setOnClickListener {}
    }
}