package br.com.fiap.eightshop

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.eightshop.databinding.ActivitySignUpBinding
import br.com.fiap.eightshop.ui.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fAuth = FirebaseAuth.getInstance();

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.etUserNameSignUp
        val email = binding.etEmailSignUp
        val password = binding.etPasswordSignUp
        val btnSignUp = binding.btCreateAccount

        btnSignUp.setOnClickListener {
            signUp(name.text.toString(), email.text.toString(), password.text.toString())
        }
    }

    private fun signUp(name:String, email:String, pass:String){
        fAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("fixo", "createUserWithEmail:success")
                        val currentUser = fAuth.currentUser
//                        updateUI(user)
                        val userProfile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                        currentUser!!.updateProfile(userProfile)
                            .addOnCompleteListener {
                                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("fixo", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@SignUpActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
//                        updateUI(null)
                    }
                })
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = fAuth.currentUser
//        updateUI(currentUser)

        Toast.makeText(
            applicationContext,
            "Entrou",
            Toast.LENGTH_LONG
        ).show()
    }


}