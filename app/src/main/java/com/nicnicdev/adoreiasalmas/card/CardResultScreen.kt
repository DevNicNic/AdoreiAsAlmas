package com.nicnicdev.adoreiasalmas.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicnicdev.adoreiasalmas.R


@Composable
fun CardResultScreen(
    cardId: Int,
    onBackToHome: () -> Unit,
    onExitApp: () -> Unit,
    viewModel: CardResultViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cardId) {
        viewModel.loadCard(cardId)
    }

    CardResultContent(
        state = state,
        onBackToHome = onBackToHome,
        onExitApp = onExitApp
    )
}

@Composable
fun CardResultContent(
    state: CardResultState,
    onBackToHome: () -> Unit,
    onExitApp: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        //  FUNDO FIXO DA TELA (a carta base do app)
        Image(
            painter = painterResource(R.drawable.conselhopretovelho_split_41),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        //  Overlay escuro (opcional, mas ajuda)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
        OutlinedButton(
            onClick = onExitApp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 4.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D4C41).copy(alpha = 0.85f),
                contentColor = Color.White
            )
        ) {
            Text(
                "Sair",
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

        //  CARTA SORTEADA (vem do state)
        state.selectedCard?.let { card ->
            Image(
                painter = painterResource(id = card.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.68f),
                contentScale = ContentScale.Fit
            )
        }

        //  BOTÕES (sempre visíveis)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        ) {
            Button(
                onClick = onBackToHome,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6D4C41).copy(alpha = 0.85f),
                    contentColor = Color.White
                )

            ) {
                Text(
                    "Nova Tiragem",
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

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardResultContentPreview() {
    CardResultContent(
        state = CardResultState(
            selectedCard = CardUiModel(
                id = 10,
                imageRes = R.drawable.conselhopretovelho_split10
            )
        ),
        onBackToHome = {},
        onExitApp = {}
    )
}
