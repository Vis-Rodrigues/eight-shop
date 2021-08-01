package br.com.fiap.eightshop.ui.product

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.data.model.Product
import br.com.fiap.eightshop.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter (var view : ProductContract.ProductView): ProductContract.ProductPresenter {

    override fun listProductByHallId(hallId: Int) {

        APIService.instance
            ?.listProducts()
            ?.enqueue(object: Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>,
                                        response: Response<List<Product>>
                )
                {
                    if(response.isSuccessful) {
                        val products = response.body()?.filter {it.hallId == hallId}
                        if (products != null) {
                            view.showData(products)
                        }
                    } else {
                        view.showError("Empresa não encontrada")
                    }
                }
                override fun onFailure(call: Call<List<Product>>,
                                       t: Throwable) {
                    view.showError(t.message.toString())
                }
            })
    }

}
