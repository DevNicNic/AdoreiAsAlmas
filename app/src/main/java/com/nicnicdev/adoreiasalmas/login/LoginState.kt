package com.nicnicdev.adoreiasalmas.login

data class LoginState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginSuccess: Boolean = false
)