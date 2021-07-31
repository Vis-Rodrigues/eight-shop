package br.com.fiap.eightshop.ui.hall

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.Hall

interface HallContract {

    interface HallView {
        fun showData(hall: List<Hall>)
        fun showError(message: String)
    }

    interface HallPresenter {
        fun listHallByCompanyId(companyId: Int) : Unit
    }

}
