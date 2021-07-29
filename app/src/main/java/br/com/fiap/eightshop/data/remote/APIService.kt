package br.com.fiap.eightshop.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIService {
        private var INSTANCE: CompanyService? = null
        val instance: CompanyService?
            get() {
                if (INSTANCE == null) {
                    val client = OkHttpClient.Builder()
                        .build()
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://6100c9e9bca46600171cf9c0.mockapi.io/eight-shop/v1/")
                        .addConverterFactory(MoshiConverterFactory.create())
                        .client(client)
                        .build()
                    INSTANCE =
                        retrofit.create(CompanyService::class.java)
                }
                return INSTANCE
            }
    }
