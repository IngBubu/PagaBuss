package com.example.pruebas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas.LoginActivity.Companion.userEmail
import com.example.pruebas.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenu.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio, R.id.nav_cuenta, R.id.nav_buscarRuta, R.id.nav_cerrarSesion,
                R.id.nav_saldo,R.id.nav_descuento, R.id.nav_sobreNosotros
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initNavigationView()
    }

    private fun initNavigationView(){
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val tvUser: TextView = headerView.findViewById(R.id.tvUser)
        tvUser.text = userEmail
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    // Handle navigation to "Inicio" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_inicio)
                }
                R.id.nav_cuenta -> {
                    // Handle navigation to "Cuenta" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_cuenta)
                }
                R.id.nav_buscarRuta -> {
                    // Handle navigation to "Buscar Ruta" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_buscarRuta)
                }
                R.id.nav_saldo -> {
                    // Handle navigation to "Saldo" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_saldo)
                }
                R.id.nav_descuento -> {
                    // Handle navigation to "Descuento" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_descuento)
                }
                R.id.nav_sobreNosotros -> {
                    // Handle navigation to "Sobre Nosotros" fragment
                    findNavController(R.id.nav_host_fragment_content_menu).navigate(R.id.nav_sobreNosotros)
                }

                R.id.nav_cerrarSesion -> {
                    signOut()
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }
    }
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}