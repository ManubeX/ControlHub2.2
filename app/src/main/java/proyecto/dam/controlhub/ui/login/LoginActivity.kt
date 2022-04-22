package proyecto.dam.controlhub.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.MainActivity
import proyecto.dam.controlhub.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_login) as NavHostFragment? ?: return

        val navController = host.navController

        viewModel.loginButtonPress.observe(this, Observer { Boolean ->
            if (Boolean){ val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()}

        })



    }



}