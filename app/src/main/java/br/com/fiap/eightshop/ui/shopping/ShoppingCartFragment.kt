package br.com.fiap.eightshop.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.eightshop.HallListActivity
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.databinding.ActivityListaTestBinding
import br.com.fiap.eightshop.databinding.FragmentShoppingCartBinding
import br.com.fiap.eightshop.ui.company.CompanyContract
import br.com.fiap.eightshop.ui.company.CompanyListAdapter
import br.com.fiap.eightshop.ui.company.CompanyPresenter

class ShoppingCartFragment : Fragment(){

    private lateinit var shoppingCartViewModel: ShppingCartViewModel
    private var _binding: FragmentShoppingCartBinding? = null
    private lateinit var companyPresenter: CompanyContract.CompanyPresenter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingCartViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(ShppingCartViewModel::class.java)

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}