package br.com.fiap.eightshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityListMerchantBinding
import br.com.fiap.eightshop.databinding.ActivityListaTestBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyListAdapter
import br.com.fiap.eightshop.ui.company.CompanyPresenter

class ListMerchantActivity : AppCompatActivity(), CompanyContract.CompanView {

    private lateinit var binding: ActivityListMerchantBinding
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListMerchantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_list_merchant)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_shopping_cart, R.id.navigation_search, R.id.navigation_ticket, R.id.navigation_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        companyPresenter = CompanyPresenter(this)
//        companyPresenter.listCompanies()
//
//        binding.listView.setOnItemClickListener() { adapterView, view, position, id ->
//            val itemAtPos = adapterView.getItemAtPosition(position)
//            Log.i(TAG, "Dados do estabelecimento $itemAtPos")
//            val company: Company = itemAtPos as Company
//
//            showHalls(company.id.toString(), company.companyName)
//        }
    }

    override fun mostrarDados(companies: List<Company>) {

//        val myListAdapter = CompanyListAdapter(this, companies)
//        binding.listView.adapter = myListAdapter

    }

    override fun mostrarErro(message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_LONG
        ).show()
    }

    override fun showHalls(id: String, name: String) {
        val intent = Intent(this@ListMerchantActivity, HallListActivity::class.java)
        intent.putExtra(R.string.company_id.toString(),id)
        intent.putExtra(R.string.company_name.toString(),name)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ListMerchantActivity"
    }
}