package com.ameen.e_store.data.model

data class CardModel(
    val cardNumber: Long,
    val cardHolderName: String,
    val cardExpiryDate: String,
    val cardType: CardTypeEnum,
)
