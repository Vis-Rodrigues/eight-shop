package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.databinding.ActivityHallListBinding
import br.com.fiap.eightshop.ui.company.extensions.visible
import br.com.fiap.eightshop.ui.hall.HallContract
import br.com.fiap.eightshop.ui.hall.HallListAdapter
import br.com.fiap.eightshop.ui.hall.HallPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class HallListActivity : AppCompatActivity(), HallContract.HallView {

    private lateinit var binding: ActivityHallListBinding
    private lateinit var hallPresenter: HallContract.HallPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHallListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_hall_list)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_shopping_cart, R.id.navigation_search, R.id.navigation_ticket, R.id.navigation_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val id= intent.getStringExtra(R.string.company_id.toString())
        val name = intent.getStringExtra(R.string.company_name.toString())

        hallPresenter = HallPresenter(this)
        if (id != null) {
            hallPresenter.listHallByCompanyId(id.toInt())
        }

    }

    override fun showData(halls: List<Hall>) {
        val hallListAdapter = HallListAdapter(this, halls)
        binding.listView.adapter = hallListAdapter
        binding.progressBar.visible(false)
    }

    override fun showError(message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_LONG
        ).show()
    }
}