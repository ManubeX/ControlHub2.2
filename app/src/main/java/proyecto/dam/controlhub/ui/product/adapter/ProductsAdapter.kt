package proyecto.dam.controlhub.ui.product.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.model.data.ProductData

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsViewHolder>() {
    private var interfaceClickRecyclerView: InterfaceClickRecyclerView? = null
    private lateinit var productList: MutableList<ProductData>
    private var positionList = 0



    constructor(productList: MutableList<ProductData>) : this() {
        this.productList = productList
    }

    constructor(interfaceClickRecyclerView: InterfaceClickRecyclerView) : this() {
        this.interfaceClickRecyclerView = interfaceClickRecyclerView
        this.productList = mutableListOf()
    }


    fun getProduct(): List<ProductData> {
        return productList
    }


    fun setProducts(products: MutableList<ProductData>) {
        productList = products
        notifyDataSetChanged()
    }

    fun addProduct(product: ProductData) {
        productList.add(product)
        notifyItemInserted(productList.size - 1)
    }

    fun deleteProduct(index: Int) {
        if (index < 0 || index >= itemCount) return;
        productList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun getIndex(): Int {
        return positionList
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
        val priceText = product.price.toString() + "€"
        holder.getName()?.text = product.name
        holder.getFamily()?.text = product.family
        holder.getPrice()?.text = priceText

    }

    override fun getItemCount(): Int {
        return productList.size
    }

}