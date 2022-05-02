package proyecto.dam.controlhub.ui.product.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.model.provider.RegisterFirebase

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsViewHolder>() {
    private var interfaceClickRecyclerView: InterfaceClickRecyclerView? = null
    private lateinit var productList: MutableList<ProductData>
    private val rf = RegisterFirebase()


    constructor(productList: MutableList<ProductData>) : this() {
        this.productList = productList
    }

    constructor(interfaceClickRecyclerView: InterfaceClickRecyclerView) : this() {

        this.interfaceClickRecyclerView = interfaceClickRecyclerView
        this.productList = mutableListOf()
        if (App.listProductsApp.size >= 0) {

            productList = App.listProductsApp
            Log.v("Index", "ListApp " + App.listProductsApp.toString())
            notifyDataSetChanged()
        }
    }



    fun setProducts(products: MutableList<ProductData>) {
        productList = products
        notifyDataSetChanged()
    }

    fun addProduct(product: ProductData) {
        productList.add(product)
        notifyItemInserted(productList.size - 1)
        rf.setProduct(product, App.userAPP.company)
    }

    fun deleteProduct(index: Int) {
        val product = App.listProductsApp[index]
        productList.removeAt(index)
        rf.deleteProduct(product, App.userAPP.company)
        notifyItemRemoved(index)
        Log.v("Index", "index delete: $index totalitem: $itemCount")
    }

    fun editProduct(index: Int,product: ProductData) {
        val oldProduct = App.listProductsApp[index]
        productList[index] = product
        rf.updateProduct(oldProduct,product,App.userAPP.company)
        notifyItemChanged(index)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        var viewP =
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        val productsViewHolder = ProductsViewHolder(viewP)
        viewP.setOnClickListener { v ->
            interfaceClickRecyclerView?.onClick(
                v,
                productList[productsViewHolder.bindingAdapterPosition],

                productsViewHolder.bindingAdapterPosition

            )


        }

        return productsViewHolder
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        var product = productList[position]
        holder.getName()?.text = product.name
        holder.getFamily()?.text = product.family
        holder.getPrice()?.text = product.price.toString()
        holder.getFormat()?.text = product.format

    }

    override fun getItemCount(): Int {
        return productList.size
    }

}