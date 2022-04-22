package proyecto.dam.controlhub.model.data

data class UserData(
    var name: String,
    var fistLastName: String,
    var secondLastName: String,
    var email: String,
    var password: String
) {
    constructor() : this("","","","","")
    var id = ""
    var imageUrl: String = ""
    var company: String = ""
    var job: String = ""
    var rules: MutableMap<String, Boolean>? = null

}

