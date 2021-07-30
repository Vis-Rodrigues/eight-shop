package br.com.fiap.eightshop.data.model

data class Product(
    val id: String,
    val name: String,
    val value: Double,
    val hallId: Int,
    val weight: String
)