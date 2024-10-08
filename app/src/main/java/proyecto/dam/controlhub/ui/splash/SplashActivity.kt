package proyecto.dam.controlhub.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import proyecto.dam.controlhub.MainActivity
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.application.App.Companion.prefs
import proyecto.dam.controlhub.model.provider.RegisterFirebase

import proyecto.dam.controlhub.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val reg = RegisterFirebase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if(prefs.getEmail().isNotEmpty()&& prefs.getPassword().isNotEmpty()){
            App.auth.signInWithEmailAndPassword(
                prefs.getEmail(),
                prefs.getPassword()).addOnCompleteListener {
                reg.getInitUser(App.auth.currentUser?.uid.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

}