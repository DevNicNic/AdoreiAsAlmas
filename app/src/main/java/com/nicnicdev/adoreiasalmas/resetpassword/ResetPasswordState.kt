package com.nicnicdev.adoreiasalmas.resetpassword

data class ResetPasswordState (
    val email: String = "",
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)