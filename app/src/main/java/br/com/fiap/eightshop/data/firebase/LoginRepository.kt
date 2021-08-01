package br.com.fiap.eightshop.data.firebase

import br.com.fiap.eightshop.data.model.LoggedInUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)
        val auth = Firebase.auth

//        fAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this,
//                OnCompleteListener<AuthResult?> { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("E", "signInWithEmail:success")
//                        val user: FirebaseUser? = fAuth.currentUser
//                        updateUiWithUser(user)
//                        val intent = Intent(this@LoginActivity, ListMerchantActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("E", "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            this@LoginActivity, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        updateUiWithUser(null)
//                    }
//
//                })

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

//    private fun login(email:String, password:String){
//        fAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this,
//                OnCompleteListener<AuthResult?> { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("E", "signInWithEmail:success")
//                        val user: FirebaseUser? = fAuth.currentUser
//                        updateUiWithUser(user)
//                        val intent = Intent(this@LoginActivity, ListMerchantActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("E", "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            this@LoginActivity, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        updateUiWithUser(null)
//                    }
//
//                })
//    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}