package proyecto.dam.controlhub.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.databinding.FragmentProductBinding
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.ui.product.adapter.InterfaceClickRecyclerView
import proyecto.dam.controlhub.ui.product.adapter.ProductsAdapter

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var productsAdapter: ProductsAdapter
    private var indexSelected = 0;
    private lateinit var listProduct : MutableList<ProductData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listProduct = mutableListOf()


        productsAdapter = ProductsAdapter(object : InterfaceClickRecyclerView {
            override fun onClick(v: View, product: ProductData, index: Int) {

                Log.v("Index", index.toString())
                setEditText(product, index)


            }
        })

        recyclerStart()

        binding.btAdd.setOnClickListener {
            val nameProduct = binding.etProductName.editText?.text.toString()
            val familyProduct = binding.etProductFamily.editText?.text.toString()
            val priceProduct = binding.etProductPrice.editText?.text.toString()

            if (nameProduct.isEmpty() || familyProduct.isEmpty() || priceProduct.isEmpty()) {
                val toast =
                    Toast.makeText(context, getString(R.string.product_empty), Toast.LENGTH_SHORT)
                toast.show()
            } else {
                productsAdapter.addProduct(
                    ProductData(
                        nameProduct,
                        familyProduct,
                        priceProduct.toDouble()
                    )
                )

                listProduct.add(ProductData(nameProduct,familyProduct,priceProduct.toDouble()))
                setEditText()
            }


        }

        binding.btRemove.setOnClickListener {

            val toast =
                Toast.makeText(context, indexSelected.toString(), Toast.LENGTH_SHORT)
            toast.show()
            if (indexSelected != -1) {
                listProduct.removeAt(indexSelected)
                productsAdapter.deleteProduct(indexSelected)
            }
        }

    }

    fun recyclerStart() {
        val recyclerView = binding.recyclerProducts
        val linLayout = LinearLayoutManager(context)

        recyclerView.layoutManager = linLayout
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = productsAdapter

    }

    private fun setEditText(product: ProductData? = null, index: Int = -1) {
        if (product == null) {
            binding.etProductName.editText?.setText("")
            binding.etProductFamily.editText?.setText("")
            binding.etProductPrice.editText?.setText("")
        } else {
            binding.etProductName.editText?.setText(product.name)
            binding.etProductFamily.editText?.setText(product.family)
            binding.etProductPrice.editText?.setText(product.price.toString())
        }

        indexSelected = index
    }

}