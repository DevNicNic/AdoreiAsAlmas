package com.nicnicdev.adoreiasalmas.home
import androidx.lifecycle.ViewModel
import com.nicnicdev.adoreiasalmas.R
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

        val cards = List(40){
            backCard
        }
        _state.update {
            it.copy(cards = cards)

        }
    }
    fun onShuffleCards() {
        val shuffledCards = _state.value.cards.shuffled()

        _state.update {
            it.copy(
                cards = shuffledCards,
                hasShuffled = true
            )
        }
    }
}