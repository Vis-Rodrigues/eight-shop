package br.com.fiap.eightshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.Hall
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
            Log.i(TAG, "Dados do estabelecimento $itemAtPos")
            val company: Company = itemAtPos as Company

            showHalls(company.id.toString(), company.companyName)
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

    override fun showHalls(id: String, name: String) {
        val intent = Intent(this@ListaTest, HallListActivity::class.java)
        intent.putExtra(R.string.company_id.toString(),id)
        intent.putExtra(R.string.company_name.toString(),name)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ListaTest"
    }
}