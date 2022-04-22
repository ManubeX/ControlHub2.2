package proyecto.dam.controlhub

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createMenuDrawer()


    }


    private fun createMenuDrawer(){

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment? ?: return

        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        val drawerLayout: DrawerLayout? = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration (
            setOf(
                R.id.nav_home, R.id.nav_List, R.id.nav_reports, R.id.nav_message, R.id.nav_profile,
                R.id.nav_settings
            ), drawerLayout)



        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)



        navController.addOnDestinationChangedListener { _, destination, _ ->

        }

    }


    private fun setupActionBar(navController: NavController,
                               appBarConfig : AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null) {
            menuInflater.inflate(R.menu.main, menu)
            return true
        }

        var view = navigationView.getHeaderView(0)
        view.findViewById<TextView>(R.id.name_user).text =
            App.userAPP?.name + "\t" + App.userAPP?.fistLastName + "\t" + App.userAPP.secondLastName
        view.findViewById<TextView>(R.id.company_name).text = App.userAPP?.company
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment_content_main))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
      /*  val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()*/
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp(appBarConfiguration)

    }









}