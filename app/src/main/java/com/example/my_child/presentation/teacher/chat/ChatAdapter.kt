package com.example.my_child.presentation.teacher.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ChatDataResponse
import com.example.my_child.utils.setupVisibility
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.chat_item_layout.view.*

class ChatAdapter(
    private val context: Context,
    private val list: ArrayList<ChatDataResponse>,
    private val isParent: Boolean
) : RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder =
        ChatItemViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.chat_item_layout, null, false)
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(list[position], isParent)
    }

    fun setMessages(it: List<ChatDataResponse>) {
        list.clear()
        list.addAll(it)
        notifyDataSetChanged()
    }

    class ChatItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view), LayoutContainer {
        fun bind(
            data: ChatDataResponse,
            isParent: Boolean
        ) {
            with(data) {
                itemView.receiver_chat_message.text = this.message
                itemView.receive_time.text = this.time
                itemView.sender_chat_message.text = this.message
                itemView.send_time.text = this.time
            }
            with(itemView) {
                if (isParent) {
                    setUI(itemView, data.sender != 0)
                } else {
                    setUI(itemView, data.sender == 0)
                }
            }
        }

        private fun setUI(view: View, visible: Boolean) {
            with(view) {
                receiver_chat_box.setupVisibility(visible)
                sender_chat_box.setupVisibility(!visible)
            }
        }

        override val containerView: View?
            get() = itemView
    }
}
