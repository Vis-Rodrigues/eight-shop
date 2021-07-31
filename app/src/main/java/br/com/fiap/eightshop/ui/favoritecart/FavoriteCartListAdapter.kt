package br.com.fiap.eightshop.ui.favoritecart

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.FavoriteCart

class FavoriteCartListAdapter(private val context: Activity,
                              private val carts: List<FavoriteCart>)
    : ArrayAdapter<FavoriteCart>(context, R.layout.favorite_shopping_cart_row, carts) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.favorite_shopping_cart_row, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.tvDate) as TextView

        val lastShopping = carts?.get(position)?.lastShopping

        titleText.text = carts?.get(position)?.name
        subtitleText.text = "Última compra $lastShopping"

        return rowView
    }
}