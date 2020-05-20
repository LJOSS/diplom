package com.example.my_child.presentation.teacher.chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import io.reactivex.Observable
import kotlinx.android.synthetic.main.top_bar.*
import java.util.concurrent.TimeUnit

class ChatActivity : BaseHomeActivity() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int, activity: FragmentActivity): Intent =
            Intent(activity, ChatActivity::class.java).apply {
                putExtra(TEACHER_ID, teacherId)
                putExtra(CHILD_ID, childId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_layout)
        val viewModel = ViewModelProvider(this, ChatViewModelFactory())
            .get(ChatViewModel::class.java)

        initChat(viewModel)
        initTopBar()
    }

    private fun initTopBar() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { onBackPressed() }
        burger.visibility = View.GONE
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

    override fun onBackPressed() {
        this.finish()
    }
}