package com.example.my_child.presentation.teacher.classlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.class_item_layout.view.*

class ClassListAdapter(
    private val context: Context,
    private val list: List<ParentDataResponse>,
    private val openChildInfo: (ParentDataResponse) -> Unit
) : RecyclerView.Adapter<ClassListAdapter.ClassItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassItemViewHolder =
        ClassItemViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.class_item_layout, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ClassItemViewHolder, position: Int) {
        val item: ParentDataResponse = list[position]
        holder.bind(item, context)
        holder.itemView.setOnClickListener { openChildInfo(item) }
    }

    class ClassItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(
            data: ParentDataResponse,
            context: Context
        ) {
            with(data) {
                Picasso
                    .with(context)
                    .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}${this.profilePicture}")
                    .error(R.drawable.error_profile_boy)
                    .into(itemView.child_image)
                itemView.child_name.text = context.getString(
                    R.string.profile_name_template,
                    childFName,
                    childLName
                )
            }
        }

        override val containerView: View?
            get() = itemView
    }
}