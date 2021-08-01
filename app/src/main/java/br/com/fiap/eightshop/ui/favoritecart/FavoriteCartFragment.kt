package br.com.fiap.eightshop.ui.favoritecart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.eightshop.data.model.FavoriteCart
import br.com.fiap.eightshop.databinding.FragmentFavoriteCartBinding
import br.com.fiap.eightshop.ui.company.extensions.visible

private const val ARG_PARAM1 = "param1"

class FavoriteCartFragment : Fragment(), FavoriteCartContract.FavoriteCartView {

    private lateinit var binding: FragmentFavoriteCartBinding
    private lateinit var cartPresenter: FavoriteCartContract.FavoriteCartPresenter

    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        binding = FragmentFavoriteCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        cartPresenter = FavoriteCartPresenter(this)
        cartPresenter.listFavoriteCartByUserId(1)

        binding.favoriteCartListView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

        }
        return root

    }

    companion object {

        @JvmStatic
        fun newInstance(userId: Int) =
            FavoriteCartFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, userId)
                }
            }
    }

    override fun showData(carts: List<FavoriteCart>) {
        if (carts.isEmpty()) {
            showMessageEmptyList()
        }else{
            val favoriteCartListAdapter = activity?.let { FavoriteCartListAdapter(it, carts) }
            binding.favoriteCartProgressBar.visible(false)
            binding.favoriteCartListView.adapter = favoriteCartListAdapter
        }

    }

    override fun showError(message: String) {

    }

    fun showMessageEmptyList() {
        binding.tvListaVazia.visible(true)
        binding.favoriteCartProgressBar.visible(false)
    }

}