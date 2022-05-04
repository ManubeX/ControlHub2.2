package proyecto.dam.controlhub.ui.lists.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.model.data.ProductData

class ProductListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameProduct = itemView.findViewById<TextView>(R.id.tv_productList_name)
    private val familyProduct = itemView.findViewById<TextView>(R.id.tv_productFamilyList)
    private val priceProduct = itemView.findViewById<TextView>(R.id.tv_product_priceList)
    private val formatProduct = itemView.findViewById<TextView>(R.id.tV_FormatList)
    private val checkItem = itemView.findViewById<ImageView>(R.id.checkItem)
    private val background = itemView.findViewById<LinearLayout>(R.id.prodruct_list_card_layout)
    fun render(product: ProductData) {

        nameProduct.text = product.name
        familyProduct.text = product.family
        priceProduct.text = product.price.toString()
        formatProduct.text = product.format
        if(product.check){
            checkItem.visibility= View.VISIBLE

        }else{
            checkItem.visibility= View.INVISIBLE

        }

    }

    fun getName(): TextView? {
        return nameProduct
    }

    fun getFamily(): TextView? {
        return familyProduct
    }

    fun getPrice(): TextView? {
        return priceProduct
    }

    fun getFormat(): TextView? {
        return formatProduct
    }

    fun getCheck(): ImageView?{
        return checkItem
    }

    fun getBackground(): LinearLayout?{
        return background
    }



}