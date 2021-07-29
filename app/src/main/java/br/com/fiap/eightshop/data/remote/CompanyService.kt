package br.com.fiap.eightshop.data.remote

import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.data.model.Company
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyService {
    @GET("/company")
    fun listCompanies(): Call<Company>
}