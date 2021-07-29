package br.com.fiap.eightshop.data

import br.com.fiap.eightshop.ui.login.UserView

/**
 * Data validation state of the login form.
 */
data class FirebaseResult(
    val success: UserView? = null,
    val error: Int? = null
)