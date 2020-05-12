package com.example.my_child.presentation.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import io.reactivex.disposables.CompositeDisposable

class WelcomeActivity : AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var bool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val viewModel =
            ViewModelProvider(this, SignUpViewModelFactory())
                .get(SignUpViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}