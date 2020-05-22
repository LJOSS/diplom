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
import com.example.my_child.utils.Constants.SEPARATOR_BABY_DIARY
import com.example.my_child.utils.Constants.SOFT
import com.example.my_child.utils.hideKeyboardNotAlways
import kotlinx.android.synthetic.main.fragment_soft.*
import kotlinx.android.synthetic.main.fragment_soft.save
import kotlinx.android.synthetic.main.fragment_soft.teacher_note
import kotlinx.android.synthetic.main.fragment_soft.time
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar.OnProgressChangeListener

class SoftFoodFragment : BaseDiaryFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            SoftFoodFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val SoftFoodFragment_TAG = "SoftFoodFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_soft, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)
        soft_rating.setOnProgressChangeListener(object : OnProgressChangeListener {
            override fun onProgressChanged(
                seekBar: DiscreteSeekBar,
                value: Int,
                fromUser: Boolean
            ) {
                seekBar.setIndicatorFormatter(
                    getFoodMessage(value).toCharArray()[0].toString().toUpperCase()
                )
            }

            override fun onStartTrackingTouch(seekBar: DiscreteSeekBar) {}
            override fun onStopTrackingTouch(seekBar: DiscreteSeekBar) {}
        })
        time.setOnClickListener { initCal(time, hour, min, true) }
        time.text = setTime(hour, min)
        save.setOnClickListener { saveSoftFood(viewModel) }
    }

    private fun saveSoftFood(viewModel: DiaryViewModel) {
        var message = "Выпил: ${getFoodMessage(soft_rating.progress)}"
        if (teacher_note.text.isNotEmpty()) {
            message =
                " $message  $SEPARATOR_BABY_DIARY  Замечания:  ${teacher_note.text.toString()
                    .trim { it <= ' ' }}"
        }

        disposable.add(
            viewModel.sendDiary(
                DiaryData(
                    childId = childId,
                    teacherId = teacherId,
                    message = message,
                    type = SOFT,
                    time = sendTime
                )
            ).subscribe({
                closeFragment()
                hideKeyboardNotAlways(requireActivity())
            }, Throwable::printStackTrace)
        )
    }
}