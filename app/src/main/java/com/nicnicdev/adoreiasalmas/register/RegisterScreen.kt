package com.nicnicdev.adoreiasalmas.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun RegisterScreen(

    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit
) {
    //aqui pegamos o estado atual da tela (email, senha, loading, erro )
    //sempre que o estado mudar a tela atualiza sozinha
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess ) {
        if (state.isSuccess) {
            Toast.makeText(
                context,
                "Cadatro realizado com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            kotlinx.coroutines.delay(2000) // espera 2 segundos
            onRegisterSuccess() //volta para o login
        }
    }

    RegisterContent(
        state = state,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = viewModel::onRegisterClick
    )

}

@Composable
fun RegisterContent(
    state: RegisterState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit
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
                    text = "Criar Conta",
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

                // campo onde o usuário digita o nome
                OutlinedTextField(
                    value = state.name, // mostra o email que está salvo no estado
                    onValueChange = { onNameChange(it) }, // salva o que a pessoa digitar
                    label = { Text("Nome", color = Color.White) },
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

                // campo onde o usuáro confirma sua senha
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = { onConfirmPasswordChange(it) },
                    label = { Text("Confirme sua senha", color = Color.White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
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
                if (!state.errorMessage.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                //registra o usuario no app
                Button(
                    onClick = onRegisterClick,
                    enabled = !state.isLoading,
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
                        "Concluir Cadastro",
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
fun RegisterScreenPreview() {
    RegisterContent(
        state = RegisterState(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            isLoading = false,
            errorMessage = null
        ),
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onRegisterClick = {}
    )
}