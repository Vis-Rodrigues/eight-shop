package br.com.fiap.eightshop

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.databinding.ActivityHallListBinding
import br.com.fiap.eightshop.ui.company.extensions.visible
import br.com.fiap.eightshop.ui.hall.HallContract
import br.com.fiap.eightshop.ui.hall.HallListAdapter
import br.com.fiap.eightshop.ui.hall.HallPresenter

class HallListActivity : AppCompatActivity(), HallContract.HallView {

    private lateinit var binding: ActivityHallListBinding
    private lateinit var hallPresenter: HallContract.HallPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHallListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id= intent.getStringExtra(R.string.company_id.toString())
        val name = intent.getStringExtra(R.string.company_name.toString())

        hallPresenter = HallPresenter(this)
        if (id != null) {
            hallPresenter.listHallByCompanyId(id.toInt())
        }

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