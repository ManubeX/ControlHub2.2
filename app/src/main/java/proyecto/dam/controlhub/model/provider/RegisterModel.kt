package proyecto.dam.controlhub.model.provider

import proyecto.dam.controlhub.model.data.UserData

interface RegisterModel {

    fun registerUser(user : UserData)

    fun getUser(id: String)

    fun dbConfigurationComp (company: String) {
    }

}