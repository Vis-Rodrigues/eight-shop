package br.com.fiap.eightshop.ui.product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Product
import br.com.fiap.eightshop.ui.company.ListMerchantActivity
import br.com.fiap.eightshop.ui.shopping.ShoppingCart
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_product_row.view.*
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.activity_product_row, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])


//        (context as MainActivity).coordinator

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {

            itemView.product_name.text = product.name
            itemView.product_price.text = "$${product.price.toString()}"
            itemView.product_weight.text = product.weight.toString()

            Picasso.get().load(product.photo).fit().into(itemView.product_image)


            //                val products = mutableListOf<Product>()
//                products.add(product)
//

//                ShoppingCart.addItem(item)


            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                itemView.addToCart.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.addItem(item)
                    //notify users
                    Snackbar.make(
                        (itemView.context as ListMerchantActivity).coordinator,
                        "${product.name} adicionado com sucesso",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart())

                }

                itemView.removeItem.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.removeItem(item, itemView.context)

                    Snackbar.make(
                        (itemView.context as ListMerchantActivity).coordinator,
                        "${product.name} removido do carrinho",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(ShoppingCart.getCart())

                }


            }).subscribe { cart ->

                var quantity = 0

                cart.forEach { cartItem ->

                    quantity += cartItem.quantity
                }

                (itemView.context as ListMerchantActivity).cart_size.text = quantity.toString()

//            }
//                Toast.makeText(itemView.context, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()


            }

        }

    }
}