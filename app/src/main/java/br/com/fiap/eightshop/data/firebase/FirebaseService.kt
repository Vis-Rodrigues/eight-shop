package br.com.fiap.eightshop.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.SignupModel
import br.com.fiap.eightshop.data.model.UserView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseService: IFirebaseService {

    private var fAuth: FirebaseAuth = Firebase.auth

    private val _firebaseResult = MutableLiveData<FirebaseResult>()
    private var fResult = FirebaseResult()
    val registrationStatus: LiveData<FirebaseResult> = _firebaseResult
    override fun signUp(model: SignupModel): FirebaseResult {
        fAuth.createUserWithEmailAndPassword(model.email, model.pass)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        setResult(FirebaseResult(success = UserView(displayName = model.name)))
                        _firebaseResult.value = getResult()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        fResult = FirebaseResult(error = R.string.signup_failed)
                        _firebaseResult.value = fResult
                    }
                })
        return getResult()
    }

    private fun setResult(result: FirebaseResult){
        fResult = result
    }

    private fun getResult(): FirebaseResult {
        return fResult
    }

    override fun login(email:String, password:String){
        fAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Login:success")
                        val user: FirebaseUser? = fAuth.currentUser
                        if (user != null) {
                            _firebaseResult.value =
                                FirebaseResult(success = UserView(displayName = user.displayName.toString()))
                        }
                    } else {
                        Log.w(TAG, "Login:failure", task.exception)
                        _firebaseResult.value = FirebaseResult(error = R.string.login_failed)
                    }
                })
        _firebaseResult
    }

    override fun signOut(){
        fAuth.signOut()
    }

    companion object {
        private const val TAG = "FirebaseService"
    }

}