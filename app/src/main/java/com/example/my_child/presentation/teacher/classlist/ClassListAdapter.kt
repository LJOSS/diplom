package com.example.my_child.presentation.teacher.classlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import kotlinx.android.extensions.LayoutContainer

class ClassListAdapter(
    private val context: Context,
    private val arrayList: ArrayList<String>
) : RecyclerView.Adapter<ClassListAdapter.ClassItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassItemViewHolder =
        ClassItemViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.class_item_layout, parent, false)
        )

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ClassItemViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    class ClassItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(s: String) {
            with(s) {

            }
        }

        override val containerView: View?
            get() = itemView
    }
}