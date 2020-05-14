package com.example.my_child.presentation.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.LoginData
import com.example.my_child.domain.model.error.ValidationResponse
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlin.math.log

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
            login(viewModel, LoginData(userName, password))
        }
    }

    private fun login(viewModel: SignUpViewModel, loginData: LoginData) {
        disposable.add(
            viewModel
                .login(loginData)
                .subscribe({
                    if (it.code == 3) {
                        Toast.makeText(this, "NE V SISTEME", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
                    }
                }, Throwable::printStackTrace)
        )
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