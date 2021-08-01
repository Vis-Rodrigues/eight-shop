package br.com.fiap.eightshop.data.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val hallId: Int,
    val weight: String,
    val photo: String
)