package com.example.my_child.presentation.parent.medicine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DayMedicine
import com.example.my_child.data.api.dto.data.MedicineData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.medicine_by_day_item.view.*

class MedicineDayAdapter(
    private val context: Context,
    private val list: List<DayMedicine>,
    private val isParent: Boolean,
    private val function: (MedicineData) -> Unit
) : RecyclerView.Adapter<MedicineDayAdapter.MedicineDayViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicineDayViewHolder =
        MedicineDayViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.medicine_by_day_item, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: MedicineDayViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.itemView.day.text = item.date
        setMedicineData(item, holder.itemView.medicine_list)
    }

    private fun setMedicineData(
        item: DayMedicine,
        medicineList: RecyclerView
    ) {
        medicineList.layoutManager = LinearLayoutManager(context)
        medicineList.adapter = MedicineAdapter(context, item.list, isParent) { function(it) }
    }

    class MedicineDayViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {

        override val containerView: View?
            get() = itemView
    }
}