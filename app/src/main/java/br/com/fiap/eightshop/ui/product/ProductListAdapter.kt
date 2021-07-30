package br.com.fiap.eightshop.ui.product

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.data.model.Product
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ProductListAdapter(private val context: Activity,
                         private val products: List<Product>)
    : ArrayAdapter<Product>(context, R.layout.product_row, products) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.product_row, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val price = rowView.findViewById(R.id.price) as TextView
        val weight = rowView.findViewById(R.id.weight) as TextView

        titleText.text = products?.get(position)?.name
//        Glide.with(context).load(products?.get(position)?.urlImage).into(imageView);
        Glide.with(context).load("https://images-submarino.b2w.io/produtos/01/00/item/130836/1/130836199P1.jpg").into(imageView);
        price.text = products?.get(position)?.value.toString()
        weight.text = products?.get(position)?.weight

        return rowView

    }
}