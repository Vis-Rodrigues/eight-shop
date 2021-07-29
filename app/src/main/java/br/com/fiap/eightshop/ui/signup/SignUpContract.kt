package br.com.fiap.eightshop.ui.signup

import br.com.fiap.eightshop.data.FirebaseResult
import br.com.fiap.eightshop.data.model.SignupModel

interface SignUpContract {
    interface SignUpView {
        fun showError(message: String)
    }

    interface SignUpPresenter {
        fun signUp(signup: SignupModel): FirebaseResult

    }
}