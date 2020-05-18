package com.example.my_child.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import androidx.core.widget.addTextChangedListener
import com.example.my_child.data.api.dto.data.LoginData
import com.example.my_child.data.api.dto.response.LoginDataResponse
import com.example.my_child.domain.model.error.ValidationResponse
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.utils.Constants.USER_ID
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

        initClicker(viewModel)
        initLoggedUser(viewModel)
        initTextChangeListeners(viewModel)
    }

    private fun initTextChangeListeners(viewModel: SignUpViewModel) {
        login_text.addTextChangedListener {
            val loginResponse =
                viewModel.checkLoginValidation(login_text.text.toString())
            checkValidationError(loginResponse, login_text_layout)
        }
        password.addTextChangedListener {
            val passwordResponse =
                viewModel.checkPasswordValidation(password.text.toString())
            checkValidationError(passwordResponse, password_layout)
        }
    }

    private fun initLoggedUser(viewModel: SignUpViewModel) {
        if (viewModel.isLogged()) {
            login(viewModel, viewModel.getLoginData())
        } else {
            layout_buttons.visibility = View.VISIBLE
        }
    }

    private fun initClicker(viewModel: SignUpViewModel) {
        login.setOnClickListener {
            val userName = login_text.text.toString()
            val password = password.text.toString()
            val loginResponse =
                viewModel.checkLoginValidation(userName)
            val passwordResponse =
                viewModel.checkPasswordValidation(password)

            if (userName.isEmpty() || password.isEmpty()) {
                return@setOnClickListener
            }
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
                        openUserActivityByType(it.data)
                        viewModel.saveUser(loginData, it.data.isParent())
                    }
                }, Throwable::printStackTrace)
        )
    }

    private fun openUserActivityByType(dataResponse: LoginDataResponse) {
        val bundle = Bundle().apply { putInt(USER_ID, dataResponse.idUser) }
        if (dataResponse.isParent()) {
            startActivity(
                Intent(this, ParentHomeActivity::class.java).putExtras(bundle)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        } else {
            startActivity(
                Intent(this, TeacherHomeActivity::class.java).putExtras(bundle)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
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