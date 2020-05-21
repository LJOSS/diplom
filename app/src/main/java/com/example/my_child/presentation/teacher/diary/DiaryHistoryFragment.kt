package com.example.my_child.presentation.teacher.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.fragment_diary_history.*

class DiaryHistoryFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            DiaryHistoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val DiaryHistoryFragment_TAG = "DiaryHistoryFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_diary_history, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)

        initList(viewModel)
    }

    private fun initList(viewModel: DiaryViewModel) {
        disposable.add(
            viewModel
                .getDiary(teacherId, childId)
                .subscribe({
                    list_diary.layoutManager = LinearLayoutManager(requireActivity())
                    list_diary.adapter = BabyDiaryAdapter(requireContext(), it)
                    list_diary.setupVisibility(it.isNotEmpty())
                    no_baby_diary.setupVisibility(it.isEmpty())
                }, Throwable::printStackTrace)
        )
    }
}