package com.ameen.e_store.data.model

import java.io.Serializable

data class UserModel(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userImage: Int,
    val userCards: List<CardModel>? = null,
    val userAddressModel: List<AddressModel>? = null
) : Serializable