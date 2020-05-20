package com.example.my_child.presentation.teacher.selectchild

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
import kotlinx.android.synthetic.main.child_item_layout.view.*

class SelectChildAdapter(
    private val context: Context,
    private val list: List<ParentDataResponse>,
    private val openChildInfo: (ParentDataResponse) -> Unit
) : RecyclerView.Adapter<SelectChildAdapter.ChildItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildItemViewHolder =
        ChildItemViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.child_item_layout, null, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChildItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, context)
        holder.itemView.setOnClickListener { openChildInfo(item) }
    }

    class ChildItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(parentDataResponse: ParentDataResponse, context: Context) {
            with(itemView) {
                child_name.text = parentDataResponse.childFName
                Picasso
                    .with(context)
                    .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}${parentDataResponse.profilePicture}")
                    .error(R.drawable.error_profile_boy)
                    .into(child_image)
            }
        }

        override val containerView: View?
            get() = itemView

    }
}