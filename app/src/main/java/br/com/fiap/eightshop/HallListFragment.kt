package br.com.fiap.eightshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.databinding.FragmentHallListBinding
import br.com.fiap.eightshop.ui.company.extensions.visible
import br.com.fiap.eightshop.ui.hall.HallContract
import br.com.fiap.eightshop.ui.hall.HallListAdapter
import br.com.fiap.eightshop.ui.hall.HallPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HallListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HallListFragment : Fragment(), HallContract.HallView {

    private lateinit var binding: FragmentHallListBinding
    private lateinit var hallPresenter: HallContract.HallPresenter

    private var companyId: String? = null
    private var companyName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            companyId = it.getString(ARG_PARAM1)
            companyName = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        binding = FragmentHallListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        hallPresenter = HallPresenter(this)
        companyId?.let { hallPresenter.listHallByCompanyId(it.toInt()) }
        loadSpinner()
        binding.hallListView.setOnItemClickListener() { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            Log.i(TAG, "Dados do estabelecimento $itemAtPos")
            val hall: Hall = itemAtPos as Hall

            showProducts(hall.id, hall.name)

        }



        return root
        
    }

    fun loadSpinner(){
        val arraySpinner = arrayOf(
            "Sem filtro", "Sem glúten", "Vegano", "Vegetariano"
        )
        val spinner: Spinner = binding.spinner1
//        spinner!!.setOnItemSelectedListener(this)
        val spinnerData = activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, arraySpinner) }
        spinnerData?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = spinnerData
    }
    fun showProducts(id: String, name: String) {

        val frag = ProductListFragment.newInstance(id, name)
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.replace(R.id.nav_host_fragment_activity_list_merchant, frag, "findThisFragment")
            ?.addToBackStack(null)?.commit()

    }

    companion object {
        private const val TAG = "HallListFragment"
        @JvmStatic
        fun newInstance(companyId: String, companyNamw: String) =
            HallListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, companyId)
                    putString(ARG_PARAM2, companyNamw)
                }
            }
    }

    override fun showData(halls: List<Hall>) {
        println("tesssss")
        val myListAdapter = activity?.let { HallListAdapter(it, halls) }
        binding.hallProgressBar.visible(false)
        binding.hallListView.adapter = myListAdapter
    }

    override fun showError(message: String) {

    }
}