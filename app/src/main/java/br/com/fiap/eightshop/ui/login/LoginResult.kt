package br.com.fiap.eightshop.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: UserView? = null,
    val error: Int? = null
)