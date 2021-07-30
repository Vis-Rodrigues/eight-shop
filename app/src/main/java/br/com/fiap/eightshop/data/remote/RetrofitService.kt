package br.com.fiap.eightshop.data.remote

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.model.Hall
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("/company")
    fun listCompanies(): Call<List<Company>>

    @GET("/hall")
    fun listHalls(): Call<List<Hall>>
}