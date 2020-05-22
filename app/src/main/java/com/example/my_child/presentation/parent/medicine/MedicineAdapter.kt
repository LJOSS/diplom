package com.example.my_child.presentation.parent.medicine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.utils.setupVisibility
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.medicine_item_layout.view.*

class MedicineAdapter(
    private val context: Context,
    private val list: List<MedicineData>,
    private val isParent: Boolean,
    private val administred: (MedicineData) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MedicineItemViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.medicine_item_layout, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MedicineItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, context, isParent)
        holder.itemView.give_medicine.setOnClickListener { administred(item) }
    }

    class MedicineItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(
            data: MedicineData,
            context: Context,
            isParent: Boolean
        ) {
            with(itemView) {
                give_medicine.setupVisibility(!isParent)
                administered.setupVisibility(isParent)
                name.text = data.name
                dosage.text = data.dosage
                frequency.text = data.frequency
                notes.text = data.notes
                administered.text = getTextAdministered(data.administred, context)
                administered.setTextColor(getTExtColorAdministered(data.administred, context))
            }
        }

        private fun getTExtColorAdministered(isAdministered: Int, context: Context): Int =
            if (isAdministered == 1) {
                context.getColor(R.color.administered)
            } else {
                context.getColor(R.color.not_administered)
        }

        private fun getTextAdministered(
            isAdministered: Int,
            context: Context
        ): String =
            if (isAdministered == 1) {
                context.getString(R.string.administered)
            } else {
                context.getString(R.string.not_administered)
            }

        override val containerView: View?
            get() = itemView
    }
}