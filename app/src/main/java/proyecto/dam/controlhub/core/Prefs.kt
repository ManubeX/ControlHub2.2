package proyecto.dam.controlhub.core

import android.content.Context

class Prefs (val context:Context){

     val SHARED_NAME = "LoginData"
     val SHARED_KEY_EMAIL = "email"
     val SHARED_KEY_PASSWORD = "password"


    val loginStorage = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    fun saveEmail(email:String, password:String){
        loginStorage.edit().putString(SHARED_KEY_EMAIL, email).apply()
        loginStorage.edit().putString(SHARED_KEY_PASSWORD, password).apply()
    }

    fun getEmail():String{
        return loginStorage.getString(SHARED_KEY_EMAIL,"")!!
    }
    fun getPassword():String{
        return loginStorage.getString(SHARED_KEY_PASSWORD,"")!!
    }



}