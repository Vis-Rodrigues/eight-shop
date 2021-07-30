package br.com.fiap.eightshop.ui.company

import br.com.fiap.eightshop.data.model.Company

interface CompanyContract {

    interface CompanView {
        fun mostrarDados(company: List<Company>)
        fun mostrarErro(mensagem: String)
        fun showHalls(id: String, name: String)
    }

    interface CompanyPresenter {
        fun listCompanies()
    }

}
