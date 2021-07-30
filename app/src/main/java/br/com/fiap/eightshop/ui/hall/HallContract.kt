package br.com.fiap.eightshop.ui.hall

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.Hall

interface HallContract {

    interface HallView {
        fun mostrarDados(hall: List<Hall>)
        fun mostrarErro(mensagem: String)
    }

    interface HallPresenter {
        fun listHallByCompanyId(companyId: Int)
    }

}
