package com.nicnicdev.adoreiasalmas.home
import androidx.lifecycle.ViewModel
import com.nicnicdev.adoreiasalmas.R
import com.nicnicdev.adoreiasalmas.card.CardUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class HomeViewModel: ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadBackCards()
    }
    private fun loadBackCards() {
        val backCard = R.drawable.conselhopretovelho_split_41 // imagem das cartinhas

        val cards = List(40) {index ->
            CardUiModel(
                id = index,
                imageRes = backCard
            )
        }
        _state.update { currentState ->
            currentState.copy(cards = cards)
        }
    }
    fun onShuffleCards() {
        _state.update { currentState ->
            currentState.copy(
                cards = currentState.cards.shuffled(),
                hasShuffled = true
            )
        }
    }
}