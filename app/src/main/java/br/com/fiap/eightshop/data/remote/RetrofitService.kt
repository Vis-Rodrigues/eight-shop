package br.com.fiap.eightshop.data.remote

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.FavoriteCart
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.data.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("/company")
    fun listCompanies(): Call<List<Company>>

    @GET("/hall")
    fun listHalls(): Call<List<Hall>>

    @GET("/favorite-cart")
    fun listFavoriteCarts(): Call<List<FavoriteCart>>

    @GET("/product")
    fun listProducts(): Call<List<Product>>

}