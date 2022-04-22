package proyecto.dam.controlhub.application

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import proyecto.dam.controlhub.core.Prefs
import proyecto.dam.controlhub.model.data.UserData

class App : Application() {
/*
User:  manuelbenlloch.proyectoilerna@gmail.com
pass: Ilerna12345
*/

 companion object{



        var userAPP = UserData()
        lateinit var prefs: Prefs
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        auth = Firebase.auth

    }

}