package br.com.fiap.eightshop.ui.company

import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyPresenter (var view : CompanyContract.CompanView): CompanyContract.CompanyPresenter {

    override fun listCompanies() {

        APIService.instance
            ?.listCompanies()
            ?.enqueue(object: Callback<List<Company>> {
                override fun onResponse(call: Call<List<Company>>,
                                    response: Response<List<Company>>
                )
                {
                    if(response.isSuccessful) {
                        response.body()?.let { view.mostrarDados(it) }
                    } else {
                        view.mostrarErro("Empresa não encontrada")
                    }
                }
                override fun onFailure(call: Call<List<Company>>,
                                       t: Throwable) {
                    view.mostrarErro(t.message.toString())
                }
            })
    }
}
