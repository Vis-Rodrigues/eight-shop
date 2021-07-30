package br.com.fiap.eightshop.ui.hall

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Hall

class HallListAdapter(private val context: Activity,
                      private val halls: List<Hall>)
    : ArrayAdapter<Hall>(context, R.layout.hall_row, halls) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.hall_row, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = halls?.get(position)?.name
        subtitleText.text = halls?.get(position)?.company

        return rowView
    }
}