package br.com.fiap.eightshop.ui.ticket

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TicketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Ticket Fragment"
    }
    val text: LiveData<String> = _text
}