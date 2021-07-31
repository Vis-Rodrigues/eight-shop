package br.com.fiap.eightshop.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.eightshop.ListMerchantActivity
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.SignUpActivity
import br.com.fiap.eightshop.data.model.LoggedInUser
import br.com.fiap.eightshop.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fAuth: FirebaseAuth
    private lateinit var user: LoggedInUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fAuth = FirebaseAuth.getInstance();

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val signUp = binding.txtCadastro

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                email.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE

                val email = email.text.toString()
                val passwordValue = password.text.toString()

                login(email, passwordValue)
            }

            signUp.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun callListMerchantActivity(){
        val intent = Intent(this@LoginActivity, ListMerchantActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val username= intent.getStringExtra(R.string.prompt_user.toString())
        if(username != null){
            binding.username.setText(intent.getStringExtra(R.string.prompt_email.toString()))
            Toast.makeText(
                applicationContext,
                "Usuário ${username} cadastrado com sucesso!",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun updateUiWithUser(email: String, name: String, userId: String): LoggedInUser {
        Log.i(TAG, "email $email")
        return LoggedInUser(userId, name, email)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun login(email:String, password:String){
        fAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i(TAG, "signInWithEmail:success")
                        val firebaseUser: FirebaseUser? = fAuth.currentUser
                        if (firebaseUser != null) {
                            Log.i(TAG, "FirebaseUser")
                            callListMerchantActivity()
                        }

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@LoginActivity, "E-mail ou senha inválidos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}



