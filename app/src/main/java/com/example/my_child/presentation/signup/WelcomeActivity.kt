package com.example.my_child.presentation.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.domain.model.error.ValidationResponse
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val viewModel =
            ViewModelProvider(this, SignUpViewModelFactory())
                .get(SignUpViewModel::class.java)

        login.setOnClickListener {
            val userName = login_text.text.toString()
            val password = password.text.toString()
            val loginResponse =
                viewModel.checkLoginValidation(userName)
            val passwordResponse =
                viewModel.checkPasswordValidation(password)

            if (checkValidationError(loginResponse, login_text_layout)) {
                return@setOnClickListener
            }
            if (checkValidationError(passwordResponse, password_layout)) {
                return@setOnClickListener
            }
            
        }
    }

    private fun checkValidationError(
        validationResponse: ValidationResponse,
        textLayout: TextInputLayout
    ): Boolean {
        textLayout.error = validationResponse.getMessageValue(this)
        return validationResponse != ValidationResponse.SUCCESS
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}