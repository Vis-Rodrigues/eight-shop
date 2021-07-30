package br.com.fiap.eightshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityCompanyBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyPresenter


private lateinit var binding: ActivityCompanyBinding
private lateinit var companyPresenter: CompanyContract.CompanyPresenter

class CompanyActivity : AppCompatActivity(), CompanyContract.CompanView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        companyPresenter = CompanyPresenter(this)
        loadData()
    }

    fun loadData(){
        println("valor**********************")
        companyPresenter.listCompanies()
    }

    override fun mostrarDados(company: List<Company>) {
        binding.txt1.text = company?.get(0)?.companyName
        binding.txt2.text = company?.get(0)?.urlImage
    }

    override fun mostrarErro(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    override fun showHalls(id: String, name: String) {
        val intent = Intent(this@CompanyActivity, HallListActivity::class.java)
        intent.putExtra("companyId",id)
        startActivity(intent)
    }
}