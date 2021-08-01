package br.com.fiap.eightshop.ui.hall

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.fiap.eightshop.R
import br.com.fiap.eightshop.data.model.ClassificationFilter
import br.com.fiap.eightshop.data.model.Hall
import br.com.fiap.eightshop.databinding.FragmentHallListBinding
import br.com.fiap.eightshop.ui.company.extensions.visible
import br.com.fiap.eightshop.ui.product.ProductListFragment
import com.bumptech.glide.Glide

private const val COMPANY_ID = "companyId"
private const val COMPANY_URL_IMAGE = "companyUrlImage"


class HallListFragment : Fragment(), HallContract.HallView, AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentHallListBinding
    private lateinit var hallPresenter: HallContract.HallPresenter

    private var companyId: String? = null
    private var companyUrlImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            companyId = it.getString(COMPANY_ID)
            companyUrlImage = it.getString(COMPANY_URL_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        binding = FragmentHallListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configuração da imagem da tela
        this?.let { Glide.with(it).load(companyUrlImage).into(binding.imageView2) };

        hallPresenter = HallPresenter(this)
        loadSpinner()
        setUpListener()

        return root
        
    }

    private fun setUpListener() {
        binding.hallListView.setOnItemClickListener() { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            Log.i(TAG, "Dados do estabelecimento $itemAtPos")
            val hall: Hall = itemAtPos as Hall

            showProducts(hall.id, hall.name)

        }

        Log.i(TAG, binding.spinner1.selectedItem.toString())

    }

    fun loadSpinner(){
        val arraySpinner = ClassificationFilter.values()
        val spinner: Spinner = binding.spinner1
        val spinnerData = activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, arraySpinner) }
        spinnerData?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = spinnerData
        spinner.onItemSelectedListener = this

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
        fun newInstance(companyId: String, companyUrlImage: String) =
            HallListFragment().apply {
                arguments = Bundle().apply {
                    putString(COMPANY_ID, companyId)
                    putString(COMPANY_URL_IMAGE, companyUrlImage)
                }
            }
    }

    override fun showData(halls: List<Hall>) {
        val myListAdapter = activity?.let { HallListAdapter(it, halls) }
        binding.hallProgressBar.visible(false)
        binding.hallListView.adapter = myListAdapter
    }

    override fun showError(message: String) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedFilter = p0?.getItemIdAtPosition(p2)?.toInt()

        if (ClassificationFilter.SEM_FILTRO.ordinal != selectedFilter) {
            companyId?.let {
                hallPresenter.listHallByCompanyIdAndCategoryId(
                    it.toInt(),
                    selectedFilter
                )
            }
        } else {
            companyId?.let { hallPresenter.listHallByCompanyId(it.toInt()) }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}