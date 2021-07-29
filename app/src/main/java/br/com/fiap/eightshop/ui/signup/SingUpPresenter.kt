package br.com.fiap.eightshop.ui.signup

import br.com.fiap.eightshop.SignUpActivity
import br.com.fiap.eightshop.data.FirebaseResult
import br.com.fiap.eightshop.data.FirebaseService
import br.com.fiap.eightshop.data.IFirebaseService
import br.com.fiap.eightshop.data.Result
import br.com.fiap.eightshop.data.model.SignupModel

class SingUpPresenter(var view: SignUpContract.SignUpView): SignUpContract.SignUpPresenter {
    override fun signUp(signup: SignupModel): FirebaseResult {
        val firebase: IFirebaseService = FirebaseService()

        return firebase.signUp(signup)
    }

}