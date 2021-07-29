package br.com.fiap.eightshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.eightshop.data.FirebaseResult
import br.com.fiap.eightshop.data.FirebaseService
import br.com.fiap.eightshop.data.model.SignupModel
import br.com.fiap.eightshop.databinding.ActivitySignUpBinding
import br.com.fiap.eightshop.ui.login.LoginActivity
import br.com.fiap.eightshop.ui.login.UserView
import br.com.fiap.eightshop.ui.signup.SignUpContract
import br.com.fiap.eightshop.ui.signup.SingUpPresenter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity(), SignUpContract.SignUpView {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signupPresenter: SignUpContract.SignUpPresenter
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.etUserNameSignUp
        val email = binding.etEmailSignUp
        val password = binding.etPasswordSignUp
        val btnSignUp = binding.btCreateAccount

        signupPresenter = SingUpPresenter(this)

        btnSignUp.setOnClickListener {
            signUp(getSignupModel(name.text.toString(), email.text.toString(), password.text.toString()))
        }
    }

    private fun signUp(model: SignupModel){
        mAuth.createUserWithEmailAndPassword(model.email, model.pass)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "createUserWithEmail:success")
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        intent.putExtra(R.string.prompt_user.toString(),model.name)
                        intent.putExtra(R.string.prompt_email.toString(),model.email)
                        startActivity(intent)
                    } else {
                        Log.e(TAG, "createUserWithEmail:failure", task.exception)
                        showError("Erro ao cadastrar usuário, tente mais tarde!")
                    }
                })
    }

    private fun getSignupModel(name:String, email:String, pass:String): SignupModel{
        return SignupModel(name, email, pass)
    }

    override fun onStart() {
        super.onStart()
        mAuth = Firebase.auth
    }

    override fun showError(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val TAG = "SingUpActivity"
    }


}