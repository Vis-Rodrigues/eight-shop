package br.com.fiap.eightshop.ui.product

import br.com.fiap.eightshop.data.model.Product

interface ProductContract {

    interface ProductView {
        fun showData(products: List<Product>)
        fun showError(message: String)
    }

    interface ProductPresenter {
        fun listProductByHallId(hallId: Int)
    }

}
