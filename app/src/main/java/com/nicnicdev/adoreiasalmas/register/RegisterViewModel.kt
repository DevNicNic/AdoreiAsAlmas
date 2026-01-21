package com.nicnicdev.adoreiasalmas.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onNameChange(newName: String) {
        _state.update {
            it.copy(name = newName, errorMessage = null)
        }
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.value = _state.value.copy(confirmPassword = confirmPassword)
    }

    fun onRegisterClick() {
        val currentState = _state.value

        // validações básicas
        if (
            currentState.name.isBlank() ||
            currentState.email.isBlank() ||
            currentState.password.isBlank() ||
            currentState.confirmPassword.isBlank()
        ) {
            _state.update {
                it.copy(errorMessage = "Preencha todos os campos")
            }
            return
        }
        if (currentState.password != currentState.confirmPassword) {
            _state.update {
                it.copy(errorMessage = "As senhas não coincidem")
            }
            return
        }
        _state.update {
            it.copy(isLoading = true, errorMessage = null)
        }
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(
                currentState.email,
                currentState.password
            )
                .addOnSuccessListener {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
                .addOnFailureListener { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Erro ao cadastrar"

                        )
                    }
                }
        }
    }
}
