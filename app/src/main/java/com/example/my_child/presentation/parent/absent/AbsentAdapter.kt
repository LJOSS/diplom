package com.example.my_child.presentation.parent.absent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.AbsentDataResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.absent_item_history.view.*

class AbsentAdapter(
    private val context: Context,
    private val list: List<AbsentDataResponse>
) : RecyclerView.Adapter<AbsentAdapter.AbsentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbsentViewHolder =
        AbsentViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.absent_item_history, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AbsentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class AbsentViewHolder(view: View) : LayoutContainer, RecyclerView.ViewHolder(view) {

        fun bind(date: AbsentDataResponse) {
            with(itemView) {
                reason.text = date.message
                this.date.text = date.date
                parent_notes.text = date.notes
            }
        }

        override val containerView: View?
            get() = itemView
    }
}