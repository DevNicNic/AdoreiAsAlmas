package com.nicnicdev.adoreiasalmas.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch




// Essa classe cuida da lógica da tela de login.
// Aqui ficam as regras do que acontece quando o usuário digita
// ou clica no botão de entrar.
class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Guarda o estado da tela de login.
    // Esse estado pode mudar (email, senha, loading, erro).
    // Só o ViewModel pode alterar isso.
    private val _state = MutableStateFlow(LoginState())

    // Esse estado é observado pela tela.
    // A tela só lê as informações, não pode modificar.
    val state: StateFlow<LoginState> = _state

    // Guarda o email que o usuário digitou.
    // e qualquer mensagem de erro é apagada
    fun onEmailChange(newEmail: String) {
        _state.update {
            it.copy(email = newEmail, errorMessage = null)
        }
    }

    // Guarda a senha que o usuário digitou.
    // Também apaga a mensagem de erro quando o usuário tenta corrigir.
    fun onPasswordChange(newPassword: String) {
        _state.update {
            it.copy(password = newPassword, errorMessage = null)
        }
    }

    // Função chamada quando o usuário clica no botão "Entrar".
    // Aqui fica a lógica principal do login.
    fun onLoginClick() {
        val currentState = _state.value

        // Verifica se o email ou a senha estão vazios.
        // Se estiverem, mostra uma mensagem de erro na tela.
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _state.update {
                it.copy(errorMessage = "Preencha todos os campos")
            }
            return
        }

        // Mostra que o app está verificando os dados do usuário.
        // A tela pode mostrar um loading e bloquear o botão.
        _state.update {
            it.copy(isLoading = true, errorMessage = null)
        }

        viewModelScope.launch {
            auth.signInWithEmailAndPassword(
                currentState.email,
                currentState.password
            )
                .addOnSuccessListener {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
                .addOnFailureListener{ exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message
                                ?: "Erro ao fazer login"
                        )
                    }
                }
        }
    }
}

