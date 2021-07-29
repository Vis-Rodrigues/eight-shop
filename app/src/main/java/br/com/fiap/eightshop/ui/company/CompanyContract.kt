package br.com.fiap.eightshop.ui.company

import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.data.model.Company

interface CompanyContract {

    interface CompanView {
        fun mostrarDados(company: Company?)
        fun mostrarErro(mensagem: String)
    }

    interface CompanyPresenter {
        fun listCompanies()
    }

}
