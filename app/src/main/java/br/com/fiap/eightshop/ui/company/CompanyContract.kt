package br.com.fiap.eightshop.ui.company

import br.com.fiap.eightshop.data.model.Company

interface CompanyContract {

    interface CompanView {
        fun showData(company: List<Company>)
        fun showError(mensagem: String)
        fun showHalls(company: Company)
    }

    interface CompanyPresenter {
        fun listCompanies()
    }

}
