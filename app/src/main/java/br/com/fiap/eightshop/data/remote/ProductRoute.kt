package br.com.fiap.eightshop.data.remote

import br.com.fiap.eightshop.data.model.Product
import retrofit2.Call
import retrofit2.http.*

interface ProductRoute {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("product")
    fun getProducts(
    ): Call<List<Product>>

}
