package com.nicnicdev.adoreiasalmas.resetpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicnicdev.adoreiasalmas.R

@Composable
fun ResetPasswordScreen (
   viewModel: ResetPasswordViewModel = viewModel ()
) {
    val state by viewModel.state.collectAsState()

    ResetPasswordContent (
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onResetClick = viewModel::onResetClick
    )

}
@Composable
fun ResetPasswordContent(
    state: ResetPasswordState,
    onEmailChange: (String) -> Unit,
    onResetClick: () -> Unit
) {
    Box (
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Redefinir Senha",
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
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { onEmailChange(it) },
                    label = { Text("Email", color = Color.White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    singleLine = true,
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
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Digite o e-mail cadastrado. Você receberá um e-mail com instruções para redefinir sua senha.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                state.errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                state.successMessage?.let {
                    Text(
                        text = it,
                        color = Color.Green,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()

                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {

                Button(
                    onClick = onResetClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D4C41).copy(alpha = 0.85f),
                        contentColor = Color.White
                    )
                ) {

                    Text(
                        "Enviar Redefinição",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(1f, 1f),
                                blurRadius = 3f
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
fun ResetPasswordPreview() {

    ResetPasswordContent(
        state = ResetPasswordState(
            email = ""
        ),
        onEmailChange = {},
        onResetClick = {}
    )
}












