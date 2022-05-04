package proyecto.dam.controlhub.ui.product.adapter


import android.text.Layout
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.model.data.ProductData


class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameProduct = itemView.findViewById<TextView>(R.id.tv_product_name)
    private val familyProduct = itemView.findViewById<TextView>(R.id.tv_productFamily)
    private val priceProduct = itemView.findViewById<TextView>(R.id.tv_product_price)
    private val formatProduct = itemView.findViewById<TextView>(R.id.tV_Format)
    fun render(product: ProductData) {

        nameProduct.text = product.name
        familyProduct.text = product.family
        priceProduct.text = product.price.toString()
        formatProduct.text = product.format

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



}