package br.com.fiap.eightshop

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.eightshop.databinding.FragmentProductListBinding
import br.com.fiap.eightshop.databinding.FragmentShopBinding
import br.com.fiap.eightshop.ui.product2.ShoppingCartActivity
import br.com.fiap.eightshop.ui.product2.ShoppingCartAdapter
import com.example.ethieladiassa.shoppingcart.ShoppingCart
import kotlinx.android.synthetic.main.activity_shopping_cart.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ShopFragment : Fragment() {

    lateinit var adapter: ShoppingCartAdapter

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentShopBinding

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
        binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = activity?.let { ShoppingCartAdapter(it, ShoppingCart.getCart()) }!!
        adapter.notifyDataSetChanged()

        binding.shoppingCartRecyclerView.adapter = adapter

        binding.shoppingCartRecyclerView.layoutManager = LinearLayoutManager(activity)

        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.price!!.toDouble()) }

        binding.totalPrice.text = "$${totalPrice}"
        setUpListener()

        return root
    }

    private fun setUpListener() {
        binding.btnCheckout.setOnClickListener {
            Toast.makeText(activity, "Compra finalizada", Toast.LENGTH_SHORT).show()
            showConfirm(binding.totalPrice.toString(), "teste")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(activity, "E agora ten que voltar finalizada", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showConfirm(id: String, name: String) {

        val frag = ConfirmPurchaseFragment.newInstance(id, name)
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.replace(R.id.nav_host_fragment_activity_list_merchant, frag, "findThisFragment")
            ?.addToBackStack(null)?.commit()

    }

    companion object {

        @JvmStatic
        fun newInstance(companyId: String, companyNamw: String) =
            ShopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, companyId)
                    putString(ARG_PARAM2, companyNamw)
                }
            }
    }

}