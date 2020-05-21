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
import com.example.my_child.utils.Constants.FECES
import kotlinx.android.synthetic.main.fragment_feces.*

class FecesFragment : BaseDiaryFragment(), View.OnClickListener {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            FecesFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val FecesFragment_TAG = "FecesFragment_TAG"
        const val YELLOW = "Желтый"
        const val GREEN = "Зеленый"
        const val BROWN = "Коричнвый"
    }

    private val array = arrayOf("Жидкий", "Обычный", "Твердый")

    var fecesColor = "Brown"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_feces, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)
        time.text = setTime(hour, min)

        time.setOnClickListener { initCal(time, hour, min, true) }

        save.setOnClickListener { saveFeces(viewModel) }
        segmented_control.setSelectedSegment(1)
        imageViewYellow.setOnClickListener(this)
        imageViewBrown.setOnClickListener(this)
        imageViewGreen.setOnClickListener(this)
        imageViewBrown.performClick()
    }

    private fun saveFeces(viewModel: DiaryViewModel) {
        val stateOfCal =
            array[segmented_control.selectedAbsolutePosition]
        if (fecesColor.isNotEmpty()) {
            sendFeces(viewModel, "Какашки - $stateOfCal. Цвет - $fecesColor")
        }
    }

    private fun sendFeces(viewModel: DiaryViewModel, message: String) {
        disposable.add(
            viewModel.sendDiary(
                DiaryData(
                    childId = childId,
                    teacherId = teacherId,
                    message = message,
                    type = FECES,
                    time = sendTime
                )
            ).subscribe({
                closeFragment()
            }, Throwable::printStackTrace)
        )
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewYellow -> {
                imageViewYellow.setImageResource(R.drawable.yellow_selected)
                imageViewBrown.setImageResource(R.drawable.brown)
                imageViewGreen.setImageResource(R.drawable.green)
                changeVisibility(YELLOW)
                fecesColor = YELLOW
            }
            R.id.imageViewBrown -> {
                imageViewYellow.setImageResource(R.drawable.yellow)
                imageViewBrown.setImageResource(R.drawable.brown_selected)
                imageViewGreen.setImageResource(R.drawable.green)
                changeVisibility(BROWN)
                fecesColor = BROWN
            }
            R.id.imageViewGreen -> {
                imageViewYellow.setImageResource(R.drawable.yellow)
                imageViewBrown.setImageResource(R.drawable.brown)
                imageViewGreen.setImageResource(R.drawable.green_selected)
                changeVisibility(GREEN)
                fecesColor = GREEN
            }
        }
    }

    private fun changeVisibility(g: String) {
        when (g) {
            YELLOW -> {
                choose_yellow.visibility = View.VISIBLE
                choose_brown.visibility = View.GONE
                choose_green.visibility = View.GONE
            }
            GREEN -> {
                choose_green.visibility = View.VISIBLE
                choose_yellow.visibility = View.GONE
                choose_brown.visibility = View.GONE
            }
            BROWN -> {
                choose_brown.visibility = View.VISIBLE
                choose_green.visibility = View.GONE
                choose_yellow.visibility = View.GONE
            }
        }
    }
}