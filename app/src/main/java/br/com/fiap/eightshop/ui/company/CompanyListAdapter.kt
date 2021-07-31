package br.com.fiap.eightshop.ui.company

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Company
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class CompanyListAdapter(private val context: Activity,
                         private val companies: List<Company>)
    : ArrayAdapter<Company>(context, R.layout.custom_list, companies) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var picasso = Picasso.get()
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.tvDate) as TextView

        titleText.text = companies?.get(position)?.companyName
//        picasso.load(companies?.get(position)?.urlImage).resize(50, 50).centerCrop().into(imageView)
        Glide.with(context).load(companies?.get(position)?.urlImage).into(imageView);
        subtitleText.text = companies?.get(position)?.description

        return rowView
    }
}