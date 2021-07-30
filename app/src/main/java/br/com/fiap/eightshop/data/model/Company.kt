package br.com.fiap.eightshop.data.model

import java.util.*

data class Company(
    val id: Int,
    val companyName: String,
    val description: String,
    val urlImage: String,
    val category: String,
    val categoryId: Int
)