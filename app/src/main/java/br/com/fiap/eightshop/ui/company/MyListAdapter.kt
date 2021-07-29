package br.com.fiap.eightshop.ui.company

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Company

class MyListAdapter(private val context: Activity, private val title: Array<String>, private val companies: List<Company>?)
    : ArrayAdapter<String>(context, R.layout.custom_list, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = companies?.get(position)?.companyName
        imageView.setImageResource(R.drawable.googleg_disabled_color_18)
        subtitleText.text = companies?.get(position)?.description

        return rowView
    }
}