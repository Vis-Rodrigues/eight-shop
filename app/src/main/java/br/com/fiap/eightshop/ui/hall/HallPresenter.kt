package br.com.fiap.eightshop.ui.hall

import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HallPresenter (var view : HallContract.HallView): HallContract.HallPresenter {

    override fun listHallByCompanyId(companyId: Int) {

        APIService.instance
            ?.listHalls()
            ?.enqueue(object: Callback<List<Hall>> {
                override fun onResponse(call: Call<List<Hall>>,
                                    response: Response<List<Hall>>
                )
                {
                    if(response.isSuccessful) {
                        val halls = response.body()?.filter {it.companyId == companyId}
                        if (halls != null) {
                            view.showData(halls)
                        }
                    } else {
                        view.showError("Corredor não encontrado")
                    }
                }
                override fun onFailure(call: Call<List<Hall>>,
                                       t: Throwable) {
                    view.showError(t.message.toString())
                }
            })
    }

}
