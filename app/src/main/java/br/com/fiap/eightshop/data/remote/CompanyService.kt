package br.com.fiap.eightshop.data.remote

import br.com.fiap.eightshop.data.model.Company
import retrofit2.Call
import retrofit2.http.GET

interface CompanyService {
    @GET("/company")
    fun listCompanies(): Call<List<Company>>
}