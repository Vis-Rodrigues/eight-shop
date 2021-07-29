package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.cep.remote.APIService
import br.com.fiap.eightshop.databinding.ActivityMainBinding
import br.com.fiap.eightshop.ui.main.MainContract
import br.com.fiap.eightshop.ui.main.MainPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainPresenter = MainPresenter(this)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btPesquisar.setOnClickListener {
            mainPresenter.pesquisar(binding.etCEP.text.toString())
        }
    }
    override fun mostrarEndereco(endereco: Endereco?) {
        binding.tvLogradouro.text = endereco?.logradouro
        binding.tvBairro.text = endereco?.bairro
        binding.tvLocalidade.text = endereco?.localidade
        binding.tvUF.text = endereco?.uf
    }
    override fun mostrarErro(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}