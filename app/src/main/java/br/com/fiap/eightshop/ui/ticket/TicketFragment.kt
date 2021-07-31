package br.com.fiap.eightshop.ui.ticket

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.eightshop.databinding.FragmentTicketBinding


class TicketFragment : Fragment() {

    private lateinit var ticketViewModel: TicketViewModel
    private lateinit var binding: FragmentTicketBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("create")


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

        binding = FragmentTicketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTicket
        ticketViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val arraySpinner = arrayOf(
            "Sem filtro", "Sem glúten", "Vegano", "Vegetariano"
        )
        val spinner: Spinner = binding.spinner1
        println("createView")
//        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, arraySpinner) }
        // Set layout to use when the list of choices appear
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.adapter = aa

        return root
    }

//    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
//        // use position to know the selected item
//    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}