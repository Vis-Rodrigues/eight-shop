package br.com.fiap.eightshop.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Funcionalidade em construção! Disponível em breve! =D"
    }
    val text: LiveData<String> = _text
}