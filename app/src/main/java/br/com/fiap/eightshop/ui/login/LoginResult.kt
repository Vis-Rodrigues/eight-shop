package br.com.fiap.eightshop.ui.login

import br.com.fiap.eightshop.data.model.UserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: UserView? = null,
    val error: Int? = null
)