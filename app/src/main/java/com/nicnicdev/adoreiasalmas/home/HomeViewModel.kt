package com.nicnicdev.adoreiasalmas.home

import androidx.lifecycle.ViewModel
import com.nicnicdev.adoreiasalmas.R
import com.nicnicdev.adoreiasalmas.card.CardDataSource
import com.nicnicdev.adoreiasalmas.card.CardUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    val allCards = CardDataSource.allCards

    init {
        loadBackCards()
        loadUserName()
    }

    private fun loadBackCards() {
        val backCard = R.drawable.conselhopretovelho_split_41 // imagem das cartinhas

        val cards = List(40) { index ->
            CardUiModel(
                id = index,
                imageRes = backCard
            )
        }
        _state.update { currentState ->
            currentState.copy(cards = cards)
        }
    }

    //embaralhas as cartas
    fun onShuffleCards() {
        _state.update { currentState ->
            currentState.copy(
                cards = currentState.cards.shuffled(),
                hasShuffled = true
            )
        }
    }

    fun onCardClicked(card: CardUiModel) {
        _state.update { currentState ->
            currentState.copy(
                selectedCard = card
            )
        }

    }

    private fun loadUserName() {

        val userName = auth.currentUser?.displayName ?: "Usuário"

        _state.update { currentState ->
            currentState.copy(
                userName = userName
            )
        }
    }


}