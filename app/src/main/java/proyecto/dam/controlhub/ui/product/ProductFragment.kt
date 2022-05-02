package proyecto.dam.controlhub.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.databinding.FragmentProductBinding
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.ui.product.adapter.InterfaceClickRecyclerView
import proyecto.dam.controlhub.ui.product.adapter.ProductsAdapter

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var productsAdapter: ProductsAdapter
    private var indexSelected = 0;
    private var editProduct = false
    private var formatProduct = ""


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

        startSpinner()

        productsAdapter = ProductsAdapter(object : InterfaceClickRecyclerView {

            override fun onClick(v: View, product: ProductData, index: Int) {

                Log.v("Index", "Index Selected $index")
                setEditText(product, index)
                editProduct = true
                binding.btAdd.setText(R.string.edit_product)

            }
        })


        recyclerStart()


        binding.btAdd.setOnClickListener {
            val nameProduct = binding.etProductName.editText?.text.toString()
            val familyProduct = binding.etProductFamily.editText?.text.toString()
            val priceProduct = binding.etProductPrice.editText?.text.toString()
            val product = ProductData(nameProduct, familyProduct, priceProduct.toDouble(), formatProduct)

            if (editProduct) {

                productsAdapter.editProduct(indexSelected, product)
                binding.btAdd.setText(R.string.product_add)
                editProduct = false
                showToast( getString(R.string.edit_product))
                setEditText()

            } else {

                var productExist = false

                for (product in App.listProductsApp) {
                    productExist = nameProduct == product.name
                }

                if (nameProduct.isEmpty() || familyProduct.isEmpty() || priceProduct.isEmpty()) {

                    showToast(getString(R.string.product_empty))

                } else if (productExist) {

                    showToast( getString(R.string.product_exist))

                } else {

                    productsAdapter.addProduct(product)
                    setEditText()
                    showToast( getString(R.string.product_add))
                }

            }


        }

        binding.btRemove.setOnClickListener {

            showToast(getString(R.string.delete_product))

            if (indexSelected != -1) {
                Log.v("Index", "Size appList: ${App.listProductsApp.size}")
                Log.v("Index", "index se selectes: $indexSelected")
                productsAdapter.deleteProduct(indexSelected)
                setEditText()
                binding.btAdd.setText(R.string.product_add)
                editProduct = false

            }
        }

    }

    private fun recyclerStart() {
        val recyclerView = binding.recyclerProducts
        val linLayout = LinearLayoutManager(context)

        recyclerView.layoutManager = linLayout
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = productsAdapter

    }

    private fun startSpinner() {
        val listFormat = resources.getStringArray(R.array.format)
        val spinner = binding.spinner
        spinner.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listFormat
        )

        spinner.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                formatProduct = listFormat[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


    }

    private fun setEditText(product: ProductData? = null, index: Int = -1) {
        if (product == null) {
            binding.etProductName.editText?.setText("")
            binding.etProductFamily.editText?.setText("")
            binding.etProductPrice.editText?.setText("")
            binding.btRemove.visibility = View.INVISIBLE
        } else {
            binding.etProductName.editText?.setText(product.name)
            binding.etProductFamily.editText?.setText(product.family)
            binding.etProductPrice.editText?.setText(product.price.toString())
            binding.btRemove.visibility = View.VISIBLE
        }

        indexSelected = index
    }

    private fun showToast(text:String){
        val toast =
            Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }




}