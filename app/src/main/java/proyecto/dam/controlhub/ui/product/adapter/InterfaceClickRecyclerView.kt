package proyecto.dam.controlhub.ui.product.adapter

import android.view.View
import proyecto.dam.controlhub.model.data.ProductData

interface InterfaceClickRecyclerView {
    fun onClick(v: View, product: ProductData, index: Int)

}