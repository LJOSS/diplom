package com.example.my_child.presentation.teacher.diary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.App
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DiaryData
import com.example.my_child.utils.Constants.FECES
import com.example.my_child.utils.Constants.SEPARATOR_BABY_DIARY
import com.example.my_child.utils.Constants.SLEEP
import com.example.my_child.utils.Constants.SOFT
import com.example.my_child.utils.Constants.SOLID
import com.example.my_child.utils.Constants.URINE
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.baby_diary_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class BabyDiaryAdapter(
    private val context: Context,
    private val list: List<DiaryData>
) : RecyclerView.Adapter<BabyDiaryAdapter.BabyDiaryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BabyDiaryItemViewHolder =
        BabyDiaryItemViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.baby_diary_item, null, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BabyDiaryItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class BabyDiaryItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        fun bind(data: DiaryData) {
            setColor(data.type, itemView)
            with(itemView) {
                datetime.text = convertDate(data.time)
                when (data.type) {
                    SLEEP, URINE -> {
                        babytype.text = data.message
                        message.visibility = View.GONE
                    }
                    FECES -> {
                        babytype.text = "Экскременты"
                        message.text = data.message
                    }
                    SOFT, SOLID -> {
                        if (!data.message.contains(SEPARATOR_BABY_DIARY)) {
                            babytype.text = data.message
                            message.visibility = View.GONE
                        } else {
                            val solidMessage: Array<String> =
                                parseText(data.message)
                            babytype.text = solidMessage[0]
                            message.text = solidMessage[1]
                        }
                    }
                }
            }
        }

        private fun setColor(typeDiary: Int, itemView: View) {
            itemView.babytype.setTextColor(getColorFromResourcesId(changeColor(typeDiary)))
            itemView.datetime.setTextColor(getColorFromResourcesId(changeColor(typeDiary)))
        }

        private fun getColorFromResourcesId(colorId: Int): Int {
            return App.applicationComponent.applicationContext().resources.getColor(colorId)
        }

        private fun changeColor(typediary: Int): Int {
            var color = 0
            when (typediary) {
                SLEEP -> {
                    color = R.color.sleep
                }
                URINE, FECES -> {
                    color = R.color.feces_urine
                }
                SOFT, SOLID -> {
                    color = R.color.soft_solid
                }
                else -> color = R.color.colorBlack
            }
            return color
        }

        private fun parseText(message: String): Array<String> {
            return message.split(SEPARATOR_BABY_DIARY).toTypedArray()
        }

        private fun convertDate(timediary: Long): String {
            val date = Date(timediary)
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            return simpleDateFormat.format(date)
        }

        override val containerView: View?
            get() = itemView
    }
}