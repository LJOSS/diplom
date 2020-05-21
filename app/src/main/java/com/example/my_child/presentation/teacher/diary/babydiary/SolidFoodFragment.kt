package com.example.my_child.presentation.teacher.diary.babydiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DiaryData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.diary.DiaryViewModel
import com.example.my_child.presentation.teacher.diary.DiaryViewModelFactory
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.SEPARATOR_BABY_DIARY
import com.example.my_child.utils.Constants.SOLID
import kotlinx.android.synthetic.main.fragment_solid.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar.OnProgressChangeListener

class SolidFoodFragment : BaseDiaryFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            SolidFoodFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val SolidFoodFragment_TAG = "SolidFoodFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_solid, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)
        solid_rating.setOnProgressChangeListener(object : OnProgressChangeListener {
            override fun onProgressChanged(
                seekBar: DiscreteSeekBar,
                value: Int,
                fromUser: Boolean
            ) {
                seekBar.setIndicatorFormatter(
                    getSolidMessage(value).toCharArray()[0].toString().toUpperCase()
                )
            }

            override fun onStartTrackingTouch(seekBar: DiscreteSeekBar) {}
            override fun onStopTrackingTouch(seekBar: DiscreteSeekBar) {}
        })
        teacher_note.isSingleLine = false
        time.setOnClickListener { initCal(time, hour, min, true) }
        time.text = setTime(hour, min)
        save.setOnClickListener { saveSolidFood(teacher_note, solid_rating, viewModel) }
    }

    private fun saveSolidFood(note: EditText, solid: DiscreteSeekBar, viewModel: DiaryViewModel) {
        var message = "Покушал: " + getSolidMessage(solid.progress)
        if (teacher_note.text.isNotEmpty()) {
            message = " $message  $SEPARATOR_BABY_DIARY  Замечания:  ${note.text.toString().trim { it <= ' ' }}"
        }
        sendSolid(viewModel, message)
    }

    private fun sendSolid(viewModel: DiaryViewModel, message: String) {
        disposable.add(
            viewModel.sendDiary(
                DiaryData(
                    childId = childId,
                    teacherId = teacherId,
                    message = message,
                    type = SOLID,
                    time = sendTime
                )
            ).subscribe({
                closeFragment()
            }, Throwable::printStackTrace)
        )
    }

    private fun getSolidMessage(progress: Int): String {
        return when (progress) {
            0 -> {
                activity!!.getString(R.string.some)
            }
            1 -> {
                activity!!.getString(R.string.most)
            }
            2 -> {
                activity!!.getString(R.string.all)
            }
            else -> {
                ""
            }
        }
    }
}