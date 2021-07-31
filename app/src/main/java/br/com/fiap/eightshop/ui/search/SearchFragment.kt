package br.com.fiap.eightshop.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.eightshop.HallListActivity
import br.com.fiap.eightshop.HallListFragment
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityListaTestBinding
import br.com.fiap.eightshop.databinding.FragmentSearchBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyListAdapter
import br.com.fiap.eightshop.ui.company.CompanyPresenter
import br.com.fiap.eightshop.ui.ticket.TicketFragment

class SearchFragment : Fragment(), CompanyContract.CompanView {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        companyPresenter = CompanyPresenter(this)
        companyPresenter.listCompanies()

        binding.listView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            Log.i(TAG, "Dados do estabelecimento $itemAtPos")
            val company: Company = itemAtPos as Company

            showHalls(company.id.toString(), company.companyName)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun mostrarDados(companies: List<Company>) {

        val myListAdapter = activity?.let { CompanyListAdapter(it, companies) }
        binding.listView.adapter = myListAdapter

    }

    override fun mostrarErro(message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_LONG
        ).show()
    }

    override fun showHalls(id: String, name: String) {

        val frag = HallListFragment.newInstance(id, name)
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.replace(R.id.nav_host_fragment_activity_list_merchant, frag, "findThisFragment")
            ?.addToBackStack(null)?.commit()

//        val intent = Intent(activity, HallListActivity::class.java)
//
//        intent.putExtra(R.string.company_id.toString(),id)
//        intent.putExtra(R.string.company_name.toString(),name)
//        startActivity(intent)

    }

    companion object {
        private const val TAG = "ShoppingCartFragment"
    }
}