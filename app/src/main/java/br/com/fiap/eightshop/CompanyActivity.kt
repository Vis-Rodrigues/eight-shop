package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityCompanyBinding
import br.com.fiap.eightshop.databinding.ActivityMainBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyPresenter
import br.com.fiap.eightshop.ui.main.MainContract
import br.com.fiap.eightshop.ui.main.MainPresenter

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
        companyPresenter.listCompanies();
    }

    override fun mostrarDados(company: Company?) {
        binding.txt1.text = company?.companyName
        binding.txt2.text = company?.urlImage
    }

    override fun mostrarErro(mensagem: String) {
        TODO("Not yet implemented")
    }
}