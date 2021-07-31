package br.com.fiap.eightshop.ui.product2

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfig {

    val BASE_URL = "https://61038d2379ed680017482537.mockapi.io/eight-shop/"

    private var retrofit: Retrofit? = null


    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitClient(context: Context): Retrofit {

        //    File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        //    Cache cache = new Cache(httpCacheDirectory, 70 * 1024 * 1024);

        val okHttpClient = OkHttpClient.Builder()
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
