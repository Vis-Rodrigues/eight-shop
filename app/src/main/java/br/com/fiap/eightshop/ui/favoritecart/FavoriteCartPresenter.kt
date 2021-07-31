package br.com.fiap.eightshop.ui.favoritecart

import android.util.Log
import br.com.fiap.eightshop.data.model.FavoriteCart
import br.com.fiap.eightshop.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteCartPresenter (var view : FavoriteCartContract.FavoriteCartView): FavoriteCartContract.FavoriteCartPresenter {

    override fun listFavoriteCartByUserId(userId: Int) {

        APIService.instance
            ?.listFavoriteCarts()
            ?.enqueue(object: Callback<List<FavoriteCart>> {
                override fun onResponse(call: Call<List<FavoriteCart>>,
                                    response: Response<List<FavoriteCart>>
                )
                {
                    Log.i(TAG, "Listando os carrinhos favoritos do usuário $userId")
                    if(response.isSuccessful) {
                        val carts = response.body()?.filter {it.userId == userId}
                        Log.i(TAG, carts.toString())
                        if (carts != null) {

                            view.showData(carts)
                        }
                    } else {
                        view.showError("Lista de Carrinho favorito apresentou erro")
                    }
                }
                override fun onFailure(call: Call<List<FavoriteCart>>,
                                       t: Throwable) {
                    view.showError(t.message.toString())
                }
            })
    }
    companion object {
        private const val TAG = "FavoriteCartPresenter"
    }

}
