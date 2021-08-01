package br.com.fiap.eightshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
import br.com.fiap.eightshop.ui.search.SearchFragment

class ListMerchantActivity : AppCompatActivity(), CompanyContract.CompanView {

    private lateinit var binding: ActivityListMerchantBinding
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // showing the back button in action bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityListMerchantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val user= intent.getStringExtra(R.string.prompt_user.toString())
        Log.i("teste", "userdata: $user")
        var bundle = Bundle()
        bundle.putString(R.string.prompt_user.toString(), "Vivi")

        var fragment= SearchFragment()
        fragment.arguments = bundle

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

//    override fun mostrarDados(companies: List<Company>) {
//
////        val myListAdapter = CompanyListAdapter(this, companies)
////        binding.listView.adapter = myListAdapter
//
//    }
//
//    override fun mostrarErro(message: String) {
//        Toast.makeText(
//            this, message, Toast.LENGTH_LONG
//        ).show()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun showData(company: List<Company>) {
        TODO("Not yet implemented")
    }

    override fun showError(mensagem: String) {
        TODO("Not yet implemented")
    }

    override fun showHalls(company: Company) {
        val intent = Intent(this@ListMerchantActivity, HallListActivity::class.java)
        intent.putExtra(R.string.company_id.toString(),company.id)
        intent.putExtra(R.string.company_name.toString(),company.description)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ListMerchantActivity"
    }
}