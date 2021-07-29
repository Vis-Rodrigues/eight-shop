package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.cep.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etCEP: EditText
    private lateinit var btPesquisar: Button
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvLocalidade: TextView
    private lateinit var tvUF: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView() // é um binding
        setUpListener()
    }

    private fun setUpView() {
        etCEP = findViewById(R.id.etCEP)
        btPesquisar = findViewById(R.id.btPesquisar)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvBairro = findViewById(R.id.tvBairro)
        tvLocalidade = findViewById(R.id.tvLocalidade)
        tvUF = findViewById(R.id.tvUF)
    }

    private fun setUpListener() {
        btPesquisar.setOnClickListener {
            pesquisar()
        }
    }

    private fun pesquisar() {

        }
}