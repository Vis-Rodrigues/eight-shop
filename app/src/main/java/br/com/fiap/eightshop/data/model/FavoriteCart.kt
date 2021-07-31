package br.com.fiap.eightshop.data.model

data class FavoriteCart(
    val id: Int,
    val name: String,
    val lastShopping: String,
    val value: Double,
    val userId: Int
)