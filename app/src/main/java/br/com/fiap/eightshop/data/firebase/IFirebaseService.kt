package br.com.fiap.eightshop.data.firebase

import br.com.fiap.eightshop.data.model.SignupModel

interface IFirebaseService {
    fun login(email:String, pass:String)
    fun signUp(model: SignupModel): FirebaseResult
    fun signOut()
}