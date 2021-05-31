package com.ameen.e_store.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val userName: String,
    val userEmail: String,
    val userImage: Int,
//    val userCards: List<CardModel>? = null,
//    val userAddressModel: List<AddressModel>? = null
) : Serializable