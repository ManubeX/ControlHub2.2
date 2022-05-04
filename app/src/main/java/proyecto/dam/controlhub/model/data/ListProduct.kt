package proyecto.dam.controlhub.model.data

import proyecto.dam.controlhub.core.DataUtil

data class ListProduct(var date: String ){
    constructor() : this (DataUtil.getData())
    val name: String =""
    var state: String = ""
    var productList: MutableList<ProductData> = mutableListOf()

}
