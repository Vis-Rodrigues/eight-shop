package br.com.fiap.eightshop.ui.favoritecart

import br.com.fiap.eightshop.data.model.FavoriteCart

interface FavoriteCartContract {

    interface FavoriteCartView {
        fun showData(hall: List<FavoriteCart>)
        fun showError(message: String)
    }

    interface FavoriteCartPresenter {
        fun listFavoriteCartByUserId(userId: Int)
    }

}
