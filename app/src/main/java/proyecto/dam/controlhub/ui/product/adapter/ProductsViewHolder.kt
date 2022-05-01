package proyecto.dam.controlhub.ui.product.adapter


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.model.data.ProductData


class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameProduct = itemView.findViewById<TextView>(R.id.tv_product_name)
    private val familyProduct = itemView.findViewById<TextView>(R.id.tv_productFamily)
    private val priceProduct = itemView.findViewById<TextView>(R.id.tv_product_price)

    fun render(product: ProductData) {
        val priceText = product.price.toString() + "€"
        nameProduct.text = product.name
        familyProduct.text = product.family
        priceProduct.text = priceText
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

}