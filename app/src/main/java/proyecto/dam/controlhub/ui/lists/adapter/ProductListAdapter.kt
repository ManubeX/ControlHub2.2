package proyecto.dam.controlhub.ui.lists.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.model.provider.RegisterFirebase
import proyecto.dam.controlhub.ui.product.adapter.InterfaceClickRecyclerView


class ProductListAdapter() :
    RecyclerView.Adapter<ProductListViewHolder>() {
    private var interfaceClickRecyclerView: InterfaceClickRecyclerView? = null
    private lateinit var productList: MutableList<ProductData>
    private val rf = RegisterFirebase()

    constructor(interfaceClickRecyclerView: InterfaceClickRecyclerView) : this() {

        this.interfaceClickRecyclerView = interfaceClickRecyclerView
        this.productList = mutableListOf()
        if (App.listProductsApp.size >= 0) {
            productList = App.listProductsApp

            Log.v("Index", "ListApp " + App.listProductsApp.toString())
            notifyDataSetChanged()
        }

    }

    fun selectProduct(index: Int, selected: Boolean){
        productList[index].check=selected
        notifyItemChanged(index)

    }
    fun getProduct(index: Int):ProductData{
        return productList[index]
    }

    fun getListCheck(){
        for(product in productList){
            product.check=false
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {

        var viewP =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_card, parent, false)
        val productListViewHolder = ProductListViewHolder(viewP)
        viewP.setOnClickListener { v ->
            interfaceClickRecyclerView?.onClick(
                v,
                productList[productListViewHolder.bindingAdapterPosition],

                productListViewHolder.bindingAdapterPosition

            )


        }

        return productListViewHolder
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        var product = productList[position]

        holder.getName()?.text = product.name
        holder.getFamily()?.text = product.family
        holder.getPrice()?.text = product.price.toString()
        holder.getFormat()?.text = product.format
        if(product.check){
            holder.getCheck()?.visibility= View.VISIBLE
            holder.getBackground()?.context?.resources?.let {
                holder.getBackground()!!
                    .setBackgroundColor(it.getColor(R.color.select))
            }

        }else{
            holder.getCheck()?.visibility= View.INVISIBLE
            holder.getBackground()?.context?.resources?.let {
                holder.getBackground()!!
                    .setBackgroundColor(it.getColor(R.color.trasnparent))
            }


        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }


}