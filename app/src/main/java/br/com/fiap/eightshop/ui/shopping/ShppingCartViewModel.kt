package br.com.fiap.eightshop.ui.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShppingCartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Shopping Cart Fragment"
    }
    val text: LiveData<String> = _text
}