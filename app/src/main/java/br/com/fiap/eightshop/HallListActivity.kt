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

        hallPresenter = HallPresenter(this)
        hallPresenter.listHallByCompanyId(1)

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

    override fun mostrarDados(halls: List<Hall>) {
        val hallListAdapter = HallListAdapter(this, halls)
        binding.listView.adapter = hallListAdapter
        binding.progressBar.visible(false)
    }

    override fun mostrarErro(mensagem: String) {
        TODO("Not yet implemented")
    }
}