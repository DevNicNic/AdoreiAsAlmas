package com.nicnicdev.adoreiasalmas.register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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
        _state.update {
            it.copy(email = email, errorMessage = null)
        }
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(password = password, errorMessage = null)
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update {
            it.copy(confirmPassword = confirmPassword, errorMessage = null)
        }
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

        auth.createUserWithEmailAndPassword(
            currentState.email,
            currentState.password
        )
            .addOnSuccessListener {

                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(currentState.name)
                    .build()

                auth.currentUser?.updateProfile(profileUpdate)
                    ?.addOnCompleteListener {

                        _state.update {
                            it.copy(
                                isLoading = false,
                                isSuccess = true // avisa que deu certo
                            )
                        }
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

