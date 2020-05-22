package com.example.my_child.presentation.parent.pickup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.PickupData
import com.example.my_child.utils.getFormattedDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pickup_item_layout.view.*

class PickupAdapter(
    private val context: Context,
    private val list: List<PickupData>
) : RecyclerView.Adapter<PickupAdapter.PickupItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickupItemViewHolder =
        PickupItemViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.pickup_item_layout, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PickupItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class PickupItemViewHolder(view: View) : LayoutContainer, RecyclerView.ViewHolder(view) {

        fun bind(data: PickupData) {
            with(itemView) {
                date.text = getFormattedDate(data.time)
                person_name.text = data.parentName
                relation.text = data.relation
                parent_notes.text = data.notes
                car_number.text = data.vehicle_number
                phone_number.text = data.contact_nmber
            }
        }

        override val containerView: View?
            get() = itemView

    }
}