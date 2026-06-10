package com.nicnicdev.adoreiasalmas.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.nicnicdev.adoreiasalmas.card.CardUiModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onCardClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        onShuffleClick = { viewModel.onShuffleCards() },
        onCardClick = { card ->
            onCardClick(card.id)
        }

    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    state: HomeState,
    onShuffleClick: () -> Unit,
    onCardClick: (CardUiModel) -> Unit
) {
    val visibleCards = state.cards.take(35)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //FUNDO
        Image(
            painter = painterResource(id = R.drawable.conselhopretovelho_split_41),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Seja bem vinda(o) ${state.userName}, faça 3 respirações profundas, " +
                            "concentre-se na situação ou em seu momento, o qual deseja um conselho. " +
                            "Clique em embaralhar e em seguida escolha uma cartinha.",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp,
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
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { onShuffleClick() },
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
                        "Embaralhar Cartas",
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

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(
                            items = visibleCards,
                            key = { _, card -> card.id }
                        ) { _, card ->
                            Image(
                                painter = painterResource(id = card.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .animateItemPlacement()
                                    .aspectRatio(0.7f)
                                    .fillMaxWidth()
                                    .clickable {
                                        onCardClick(card)
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }

                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        state = HomeState(
            cards = List(35) {
                com.nicnicdev.adoreiasalmas.card.CardUiModel(
                    id = it,
                    imageRes = R.drawable.conselhopretovelho_split_41
                )
            }
        ),
        onShuffleClick = {},
        onCardClick = {}
    )
}