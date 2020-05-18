package com.example.my_child.presentation.teacher.homework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.HomeworkDataResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.homework_item_layout.view.*

class HomeworkAdapter(
    private val context: Context,
    private val list: List<HomeworkDataResponse>
) : RecyclerView.Adapter<HomeworkAdapter.HomeWorkItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeWorkItem(
            LayoutInflater.from(context)
                .inflate(R.layout.homework_item_layout, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HomeWorkItem, position: Int) {
        holder.bind(list[position])
    }

    class HomeWorkItem(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(homeworkDataResponse: HomeworkDataResponse) {
            with(homeworkDataResponse) {
                itemView.homework_title.text = title
                itemView.homework_description.text = message
                itemView.homework_date.text = date
            }
        }

        override val containerView: View?
            get() = itemView

    }
}