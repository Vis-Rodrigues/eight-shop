package br.com.fiap.eightshop.ui.signup

import br.com.fiap.eightshop.data.firebase.FirebaseResult
import br.com.fiap.eightshop.data.firebase.FirebaseService
import br.com.fiap.eightshop.data.firebase.IFirebaseService
import br.com.fiap.eightshop.data.model.SignupModel

class SingUpPresenter(var view: SignUpContract.SignUpView): SignUpContract.SignUpPresenter {
    override fun signUp(signup: SignupModel): FirebaseResult {
        val firebase: IFirebaseService = FirebaseService()

        return firebase.signUp(signup)
    }

}