package com.example.my_child.presentation.teacher.diary.babydiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DiaryData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.diary.DiaryViewModel
import com.example.my_child.presentation.teacher.diary.DiaryViewModelFactory
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.URINE
import kotlinx.android.synthetic.main.fragment_urine.*

class UrineFragment : BaseDiaryFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            UrineFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val UrineFragment_TAG = "UrineFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_urine, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)

        time.text = setTime(hour, min)

        time.setOnClickListener { initCal(time, hour, min, true) }

        save.setOnClickListener { saveUrine(viewModel) }
    }

    private fun saveUrine(viewModel: DiaryViewModel) {
        sendUrine(viewModel)
    }

    private fun sendUrine(viewModel: DiaryViewModel) {
        disposable.add(
            viewModel.sendDiary(
                DiaryData(
                    childId = childId,
                    teacherId = teacherId,
                    message = "Пипи",
                    type = URINE,
                    time = sendTime
                )
            ).subscribe({
                closeFragment()
            }, Throwable::printStackTrace)
        )
    }
}