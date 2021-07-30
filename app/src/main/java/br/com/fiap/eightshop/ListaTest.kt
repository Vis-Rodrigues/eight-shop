package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityListaTestBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyPresenter
import br.com.fiap.eightshop.ui.company.CompanyListAdapter

class ListaTest : AppCompatActivity(), CompanyContract.CompanView{

    private lateinit var binding: ActivityListaTestBinding
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        companyPresenter = CompanyPresenter(this)
        companyPresenter.listCompanies()

        binding.listView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(
                this,
                "Click on item at $itemAtPos its item id $itemIdAtPos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun mostrarDados(companies: List<Company>) {

        val myListAdapter = CompanyListAdapter(this, companies)
        binding.listView.adapter = myListAdapter

    }

    override fun mostrarErro(message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_LONG
        ).show()
    }
}