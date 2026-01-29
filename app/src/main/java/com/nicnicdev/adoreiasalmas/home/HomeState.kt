package com.nicnicdev.adoreiasalmas.home

import com.nicnicdev.adoreiasalmas.card.CardUiModel

data class HomeState(
    val userName: String = "Nic", // nome para saudação
    val cards: List<CardUiModel> = emptyList(), // cartas na tela
    val hasShuffled: Boolean = false // saber se o usuario ja clicou
 )
