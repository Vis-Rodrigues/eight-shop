package br.com.fiap.eightshop.data.firebase

import br.com.fiap.eightshop.data.model.UserView

/**
 * Data validation state of the login form.
 */
data class FirebaseResult(
    val success: UserView? = null,
    val error: Int? = null
)