package com.example.my_child.domain.model.error

import android.content.Context
import androidx.annotation.StringRes
import com.example.my_child.App
import com.example.my_child.R
import java.lang.Exception

enum class ValidationResponse(
    val errorId: Int,
    @StringRes private val messageId: Int?
) {
    SUCCESS(-1, null),

    LOGIN_LENGTH_RESPONSE(
        errorId = 1,
        messageId = R.string.login_min_length_error
    ),
    PASSWORD_LENGTH_RESPONSE(
        errorId = 2,
        messageId = R.string.pass_min_length_error
    ),
    PASSWORD_LETTERS_RESPONSE(
        errorId = 2,
        messageId = R.string.password_letters_error
    ),
    NOT_EQUALS_PASSWORDS_RESPONSE(
        errorId = 3,
        messageId = R.string.not_equals_passwords_error
    ),
    USERNAME_NOT_CONTAINS(
        errorId = 4,
        messageId = R.string.check_username
    ),
    PASSWORD_NOT_CONTAINS(
        errorId = 5,
        messageId = R.string.check_password
    );

    fun getMessageValue(context: Context = App.applicationComponent.applicationContext()): String? =
        try {
            context.getString(messageId!!)
        } catch (e: Exception) {
            null
        }
}