package com.nicnicdev.adoreiasalmas.card

import androidx.lifecycle.ViewModel
import com.nicnicdev.adoreiasalmas.card.CardDataSource.allCards
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardResultViewModel : ViewModel() {

    private val _state = MutableStateFlow(CardResultState())
    val state: StateFlow<CardResultState> = _state

    fun loadCard(cardId: Int) {
        val card = allCards.find { it.id == cardId }

        _state.value = CardResultState(
            selectedCard = card
        )
    }

}