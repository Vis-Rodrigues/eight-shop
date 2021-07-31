package br.com.fiap.eightshop.ui.ticket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.eightshop.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private lateinit var ticketViewModel: TicketViewModel
    private var _binding: FragmentTicketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val COMPANY_ID : String = "companyId"
    private val COMPANY_NAME : String = "companyName"

    companion object {

        @JvmStatic
        fun newInstance(companyId: String, companyName : String) = TicketFragment().apply {
            arguments = Bundle().apply {
                putString(COMPANY_ID, companyId)
                putString(COMPANY_NAME, companyId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ticketViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                TicketViewModel::class.java
            )

        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTicket
        ticketViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    private var valor : String? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getString("teste")?.let {
            valor = it
        }
        println("dfdfdfdfdfd $valor")
        binding.textTicket.text = valor
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}