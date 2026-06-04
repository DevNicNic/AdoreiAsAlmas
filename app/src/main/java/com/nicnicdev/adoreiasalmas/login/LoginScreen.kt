package com.nicnicdev.adoreiasalmas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicnicdev.adoreiasalmas.R

//Função Tela de Login
@Composable
fun LoginScreen(
    // aqui a tela recebe o ViewModel responsavel pela lógica do login
    loginViewModel: LoginViewModel = viewModel(),
    onCreateAccountClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    //aqui pegamos o estado atual da tela (email, senha, loading, erro )
    //sempre que o estado mudar a tela atualiza sozinha
    val state by loginViewModel.state.collectAsState()

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            onLoginSuccess()
        }
    }


    LoginContent(
        state = state,
        onEmailChange = loginViewModel::onEmailChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onLoginClick = loginViewModel::onLoginClick,
        onForgotPasswordClick= onForgotPasswordClick,
        onCreateAccountClick = onCreateAccountClick // passa pro conteudo
    )


}
@Composable
fun LoginContent(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {


        Box(
            modifier = Modifier.fillMaxSize()

        ) {
            Image(
                painter = painterResource(id = R.drawable.conselhopretovelho_split_41),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )

            // essa coluna organiza os elemntos um em baixo do outro
            Column(
                modifier = Modifier
                    .fillMaxSize() // ocupa a tela inteira
                    .padding(24.dp)


            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Título da tela
                    Text(
                        text = "Acesse sua Conta",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 26.sp,
                            color = Color.White,
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    // espaço entre os elementos
                    Spacer(modifier = Modifier.height(16.dp))

                    // campo onde o usuário digita o email
                    OutlinedTextField(
                        value = state.email, // mostra o email que está salvo no estado
                        onValueChange = { onEmailChange(it) }, // salva o que a pessoa digitar
                        label = { Text("Email", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp), // deixa o campo mais alto e confortavel
                        singleLine = true,
                        shape = MaterialTheme.shapes.large, // arredonda bem as laterais
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF6D4C41).copy(alpha = 0.6f),
                            unfocusedContainerColor = Color(0xFF6D4C41).copy(alpha = 0.4f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF6D4C41),
                            unfocusedBorderColor = Color(0xFF6D4C41).copy(alpha = 0.6f),
                            cursorColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // campo onde o usuário digita a senha
                    OutlinedTextField(
                        value = state.password, //mostra a senha salva no estado
                        onValueChange = { onPasswordChange(it) }, // atualiza a senha no ViewModel
                        label = { Text("Senha", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(), //  esconde os caracteres
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password //  teclado de senha
                        ),
                        shape = MaterialTheme.shapes.large,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF6D4C41).copy(alpha = 0.6f),
                            unfocusedContainerColor = Color(0xFF6D4C41).copy(alpha = 0.4f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF6D4C41),
                            unfocusedBorderColor = Color(0xFF6D4C41).copy(alpha = 0.6f),
                            cursorColor = Color.White
                        )
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        // botão entrar
                        Button(
                            onClick = { onLoginClick() },// chama a lógica de login
                            enabled = !state.isLoading, // desativa o botão enquanto esté carregando
                            contentPadding = PaddingValues(
                                vertical = 8.dp,
                                horizontal = 20.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6D4C41).copy(alpha = 0.85f),
                                contentColor = Color.White
                            )
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
                                Text(
                                    "Entrar",
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        shadow = Shadow(
                                            color = Color.Black,      // sombra preta
                                            offset = Offset(1f, 1f),  // leve deslocamento
                                            blurRadius = 3f           // sombra suave
                                        )
                                    )
                                )
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(6.dp))

                // texto para usuário recuperar a senha (ainda sem ação)
                Text(
                    text = "Esqueci a Senha",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onForgotPasswordClick()
                        },
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        shadow = Shadow(
                            color = Color.Black,      // sombra preta
                            offset = Offset(1f, 1f),  // deslocamento da sombra
                            blurRadius = 3f           // leve desfoque
                        )
                    )

                )
                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    //botão para levar o usuário a tela de cadastro
                    Button(
                        onClick = { onCreateAccountClick() },
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                            horizontal = 20.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6D4C41).copy(alpha = 0.85f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Cadastrar",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                shadow = Shadow(
                                    color = Color.Black,      // sombra preta
                                    offset = Offset(1f, 1f),  // leve deslocamento
                                    blurRadius = 3f           // sombra suave
                                )
                            )
                        )

                    }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginContent(
        state = LoginState(
            email = "",
            password = "",
            isLoading = false,
            errorMessage = ""
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onCreateAccountClick = {},
        onForgotPasswordClick = {}
    )
}