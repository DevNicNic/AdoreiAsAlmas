package com.nicnicdev.adoreiasalmas.card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardResultViewModel : ViewModel() {

    private val _state = MutableStateFlow(CardResultState())
    val state: StateFlow<CardResultState> = _state

    fun setSelectedCard(card: CardUiModel) {
        _state.value = CardResultState(selectedCard = card)
    }
}