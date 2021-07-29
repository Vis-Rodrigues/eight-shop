package br.com.fiap.eightshop.ui.main

import br.com.fiap.eightshop.cep.Endereco
import br.com.fiap.eightshop.cep.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (var view : MainContract.MainView): MainContract.MainPresenter {
    override fun pesquisar(cep: String) {
        if(cep.isEmpty()) {
            view.mostrarErro("Informe o CEP")
        } else {
            APIService.instance
                ?.pesquisar(cep)
                ?.enqueue(object: Callback<Endereco> {
                    override fun onResponse(call: Call<Endereco>,
                                            response: Response<Endereco>
                    )
                    {
                        if(response.isSuccessful) {
                            view.mostrarEndereco(response.body())
                        } else {
                            view.mostrarErro("Endereço não encontrado")
                        }
                    }
                    override fun onFailure(call: Call<Endereco>,
                                           t: Throwable) {
                        view.mostrarErro(t.message.toString())
                    }
                })
        }
    }
}
