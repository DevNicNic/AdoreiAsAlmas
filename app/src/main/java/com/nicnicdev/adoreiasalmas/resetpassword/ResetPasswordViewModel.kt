package com.nicnicdev.adoreiasalmas.resetpassword

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResetPasswordViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(ResetPasswordState())
    val state: StateFlow<ResetPasswordState> = _state

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onResetClick() {

        if (_state.value.email.isBlank()) {

            _state.value = _state.value.copy(
                errorMessage = "Digite seu email"
            )
            return
        }

        auth.sendPasswordResetEmail(_state.value.email)
            .addOnSuccessListener {
                _state.value = _state.value
                    .copy(
                        successMessage = "Email de redefinição enviado!",
                        errorMessage = null
                    )


            }
            .addOnFailureListener { exception ->

                _state.value = _state.value.copy(
                    errorMessage = exception.message ?: "Erro ao enviar email!",
                    successMessage = null
                )

            }

    }
}