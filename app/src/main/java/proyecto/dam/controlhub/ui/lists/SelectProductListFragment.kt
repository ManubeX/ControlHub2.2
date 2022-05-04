package proyecto.dam.controlhub.ui.lists

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.databinding.FragmentListasBinding
import proyecto.dam.controlhub.databinding.FragmentSelectProductListBinding
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.ui.lists.adapter.ProductListAdapter
import proyecto.dam.controlhub.ui.product.adapter.InterfaceClickRecyclerView
import proyecto.dam.controlhub.ui.product.adapter.ProductsAdapter


class SelectProductListFragment : Fragment() {
    private var _binding: FragmentSelectProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        productListAdapter = ProductListAdapter(object : InterfaceClickRecyclerView {

            override fun onClick(v: View, product: ProductData, index: Int) {

                if(productListAdapter.getProduct(index).check) productListAdapter.selectProduct(index,false)
                else productListAdapter.selectProduct(index,true)

            }
        })
        recyclerStart()}


    private fun recyclerStart() {
        val recyclerView = binding.recyclerView
        val linLayout = LinearLayoutManager(context)

        recyclerView.layoutManager = linLayout
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = productListAdapter
    }
}