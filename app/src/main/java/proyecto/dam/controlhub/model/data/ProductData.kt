package proyecto.dam.controlhub.model.data

data class ProductData(
    val name: String,
    val family: String,
    val price: Double,
    val format : String
){
    constructor() : this("","",0.0,"")
    val produced = 0.0
    val sold = 0.0


}
