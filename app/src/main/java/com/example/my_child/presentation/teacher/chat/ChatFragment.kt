package com.example.my_child.presentation.teacher.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ChatFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                    putInt(CHILD_ID, childId)
                }
            }

        const val ChatFragment_TAG = "ChatFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.chat_layout, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, ChatViewModelFactory())
            .get(ChatViewModel::class.java)

        initChat(viewModel)
    }

    private fun initChat(viewModel: ChatViewModel) {
        disposable.add(
            Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap { viewModel.getChat(teacherId, childId) }
                .subscribe({

                }, Throwable::printStackTrace)
        )
    }
}