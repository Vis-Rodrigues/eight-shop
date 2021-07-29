package br.com.fiap.eightshop.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class SignupModel(
    val name:String,
    val email:String,
    val pass:String
)