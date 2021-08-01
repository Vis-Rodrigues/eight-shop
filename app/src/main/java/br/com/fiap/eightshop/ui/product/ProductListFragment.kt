package br.com.fiap.eightshop.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Product
import br.com.fiap.eightshop.databinding.FragmentProductListBinding
import br.com.fiap.eightshop.data.remote.APIConfig
import br.com.fiap.eightshop.data.remote.ProductRoute
import br.com.fiap.eightshop.ui.shopping.ShopFragment
import br.com.fiap.eightshop.ui.shopping.ShoppingCart
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_product_list.*
import retrofit2.Call
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProductListBinding

    private lateinit var apiService: ProductRoute
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        Paper.init(activity)
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        apiService = activity?.let { APIConfig.getRetrofitClient(it).create(ProductRoute::class.java) }!!

        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(activity!!,
            R.color.colorPrimary
        ))

        binding.swipeRefreshLayout.isRefreshing = true

//        val layoutManager = StaggeredGridLayoutManager(this, Lin)

        binding.productsRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        binding.cartSize.text = ShoppingCart.getShoppingCartSize().toString()

        getProducts()


        binding.showCart.setOnClickListener {

            showShoppingCart()
        }

        return root

    }

    private fun getProducts() {

        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                print(t.message)
                Log.d("data error", "erro")
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                swipeRefreshLayout.isRefreshing = false
                swipeRefreshLayout.isEnabled = false

                products = response.body()!!

                productAdapter = activity?.let { ProductAdapter(it, products) }!!

                products_recyclerview.adapter = productAdapter

                productAdapter.notifyDataSetChanged()

            }

        })
    }

//    override fun showData(halls: List<Hall>) {
//        println("tesssss")
//        val myListAdapter = activity?.let { HallListAdapter(it, halls) }
//        binding.hallProgressBar.visible(false)
//        binding.hallListView.adapter = myListAdapter
//    }
//
//    override fun showError(message: String) {
//
//    }

    companion object {

        private const val TAG = "ShoppingCartFragment"

        @JvmStatic
        fun newInstance(hallId: String, param2: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, hallId)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun showShoppingCart() {

        val frag = ShopFragment.newInstance("1", "nome")
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.replace(R.id.nav_host_fragment_activity_list_merchant, frag, "findThisFragment")
            ?.addToBackStack(null)?.commit()

    }

}