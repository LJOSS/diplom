package com.example.my_child.domain

import com.example.my_child.domain.model.error.ValidationResponse

class ValidationManager {

    companion object {
        const val LOGIN_LENGTH_MIN_VALUE = 4
        const val PASSWORD_LENGTH_MIN_VALUE = 6
    }

    // Проверка на длину(>4)
    fun checkLoginValidation(login: String): ValidationResponse =
        if (login.isEmpty()) {
            ValidationResponse.SUCCESS
        } else if (checkLength(login, LOGIN_LENGTH_MIN_VALUE)) {
            ValidationResponse.LOGIN_LENGTH_RESPONSE
        } else {
            ValidationResponse.SUCCESS
        }

    private fun checkLength(value: String, lengthMinValue: Int): Boolean =
        value.length < lengthMinValue

    // Проверка на длину(>6)
    // Проверка на [a-zA-z0-9]
    fun checkPasswordValidation(password: String): ValidationResponse =
        if (password.isEmpty()) {
            ValidationResponse.SUCCESS
        } else if (checkLength(password, PASSWORD_LENGTH_MIN_VALUE)) {
            ValidationResponse.PASSWORD_LENGTH_RESPONSE
        } else if (!password.any { it.isDigit() }) {
            ValidationResponse.PASSWORD_LETTERS_RESPONSE
        } else if (!password.any { it.isLowerCase() }) {
            ValidationResponse.PASSWORD_LETTERS_RESPONSE
        } else if (!password.any { it.isUpperCase() }) {
            ValidationResponse.PASSWORD_LETTERS_RESPONSE
        } else {
            ValidationResponse.SUCCESS
        }

    // Проверка на совпадение паролей
    fun checkConfirmPasswordValidation(pass: String, confirmPass: String): ValidationResponse =
        if (pass == confirmPass) {
            ValidationResponse.SUCCESS
        } else {
            ValidationResponse.NOT_EQUALS_PASSWORDS_RESPONSE
        }
}