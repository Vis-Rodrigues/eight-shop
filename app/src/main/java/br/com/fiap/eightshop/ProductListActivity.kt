package br.com.fiap.eightshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.eightshop.data.model.Product
import br.com.fiap.eightshop.databinding.ActivityProductListBinding
import br.com.fiap.eightshop.ui.company.extensions.visible
import br.com.fiap.eightshop.ui.product.ProductContract
import br.com.fiap.eightshop.ui.product.ProductListAdapter
import br.com.fiap.eightshop.ui.product.ProductPresenter

class ProductListActivity : AppCompatActivity(), ProductContract.ProductView {
    private lateinit var binding: ActivityProductListBinding
    private lateinit var productPresenter: ProductContract.ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productPresenter = ProductPresenter(this)
        productPresenter.listProductByHallId(1)

        setUpListener()
    }

    private fun setUpListener() {
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

    override fun showData(halls: List<Product>) {
        val productListAdapter = ProductListAdapter(this, halls)
        binding.listView.adapter = productListAdapter
        binding.progressBar.visible(false)
    }

    override fun showError(message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_LONG
        ).show()
    }
}