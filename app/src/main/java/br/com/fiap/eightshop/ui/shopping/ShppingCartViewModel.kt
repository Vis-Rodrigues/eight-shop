package br.com.fiap.eightshop.ui.shopping

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ShppingCartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Shopping Cart Fragment"
    }
    val text: LiveData<String> = _text
}