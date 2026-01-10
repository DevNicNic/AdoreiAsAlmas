package com.nicnicdev.adoreiasalmas.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

//Função Tela de Login
@Composable
fun LoginScreen(
    // aqui a tela recebe o ViewModel responsavel pela lógica do login
    loginViewModel: LoginViewModel = viewModel()
) {
    //aqui pegamos o estado atual da tela (email, senha, loading, erro )
    //sempre que o estado mudar a tela atualiza sozinha
    val state by loginViewModel.state.collectAsState()

    // essa coluna organiza os elemntos um em baixo do outro
    Column(
        modifier = Modifier
            .fillMaxSize() // ocupa a tela inteira
            .padding(24.dp), // da um espacinho nas bordas
        verticalArrangement = Arrangement.Center //centraliza tudo verticalmente
    ) {
        // Título da tela
        Text(
            text = "Acesse sua Conta",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        // espaço entre os elementos
        Spacer(modifier = Modifier.height(16.dp))

        // campo onde o usuário digita o email
        OutlinedTextField(
            value = state.email, // mostra o email que está salvo no estado
            onValueChange = { loginViewModel.onEmailChange(it) }, // salva o que a pessoa digitar
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true

        )
        Spacer(modifier = Modifier.height(8.dp))

        // campo onde o usuário digita a senha
        OutlinedTextField(
            value = state.password, //mostra a senha salva no estado
            onValueChange = { loginViewModel.onPasswordChange(it) }, // atualiza a senha no ViewModel
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Se existir alguma mensagem de erro , ela aparece aqui
        // Se não tiver erro, esse bloco  não aparece
        state.errorMessage?.let { errorMessage ->

            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error // cor vermelha de erro
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
        // botão entrar
        Button(
            onClick = { loginViewModel.onLoginClick() },// chama a lógica de login
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading // desativa o botão enquanto esté carregando

        )
        {
            // se estiver carregando, mostra um loading
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                // Se não estiver carregando , mostra o texto normal
                Text("Entrar")
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

        // texto para usuário recuperar a senha (ainda sem ação)
        Text(
            text = "Esqueci a Senha",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        //botão para levar o usuário a tela de cadastro
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")

        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}