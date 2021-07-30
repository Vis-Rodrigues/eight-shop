package br.com.fiap.eightshop.ui.main

import br.com.fiap.eightshop.cep.Endereco

interface MainContract {

    interface MainView {
        fun mostrarEndereco(endereco: Endereco?)
        fun mostrarErro(mensagem: String)
    }
    interface MainPresenter {
        fun pesquisar(cep: String)
    }

}
