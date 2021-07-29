package br.com.fiap.eightshop.ui.company

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.ui.company.extensions.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.company_row.view.*

class CompanyListAdapter(
    private val picasso: Picasso
) : RecyclerView.Adapter<CompanyListAdapter.ViewHolder>() {

    var companies: List<Company> = listOf()

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.company_row)) {

        fun bind(company: Company) = with(itemView) {
            tvTitleProduct.text = company.companyName
            tvDescriptionProduct.text = company.description
            picasso.load(company.urlImage).into(ivPhotoProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = companies.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(companies[position])
}
