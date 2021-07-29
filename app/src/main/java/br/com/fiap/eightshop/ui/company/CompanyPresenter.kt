package br.com.fiap.eightshop.ui.company

import android.os.Parcel
import android.os.Parcelable
import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.cep.remote.APIService
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.remote.CompanyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyPresenter (var view : CompanyContract.CompanView): CompanyContract.CompanyPresenter {

    override fun listCompanies() {

        CompanyApiService.instance
            ?.listCompanies()
            ?.enqueue(object: Callback<Company> {
            override fun onResponse(call: Call<Company>,
                                    response: Response<Company>
            )
            {
                if(response.isSuccessful) {
                    view.mostrarDados(response.body())
                } else {
                    view.mostrarErro("Empresa não encontrada")
                }
            }
            override fun onFailure(call: Call<Company>,
                                   t: Throwable) {
                view.mostrarErro(t.message.toString())
            }
        })
    }
}
