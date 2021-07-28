package br.com.fiap.eightshop.ui.ticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Ticket Fragment"
    }
    val text: LiveData<String> = _text
}