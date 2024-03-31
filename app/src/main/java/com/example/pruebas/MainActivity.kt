package com.example.pruebas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pruebas.LoginActivity.Companion.userEmail
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initNavigationView()
    }


    private fun initToolbar() {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.tituloappbarr, R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)

        toggle.syncState()

    }

    private fun initNavigationView() {
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var headerView: View =
            LayoutInflater.from(this).inflate(R.layout.nav_header_main, navigationView, false)
        navigationView.removeHeaderView(headerView)
        navigationView.addHeaderView(headerView)

        var tvUser: TextView = headerView.findViewById(R.id.tvUser)
        tvUser.text = userEmail
    }

    fun callsignOut(view: View) {
        signOut()
    }

    private fun signOut() {
        userEmail = ""
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.nav_inicio -> callInicioActivity()
            R.id.nav_cerrarSesion -> signOut()
            R.id.nav_buscarRuta -> callBuscarRuta()
            R.id.nav_cuenta -> callCuenta()
            R.id.nav_saldo -> callSaldo()
            R.id.nav_descuento -> callDescuento()
            R.id.nav_sobreNosotros -> callSobreNosotros()
        }

        drawer.closeDrawer(GravityCompat.START)

        return true
    }
    private fun callInicioActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun callBuscarRuta(){
        val intent = Intent(this, BuscarRutaActivity::class.java)
        startActivity(intent)
    }
    private fun callCuenta(){
        val intent = Intent(this, CuentaActivity::class.java)
        startActivity(intent)
    }
    private fun callSaldo(){
        val intent = Intent(this, SaldoActivity::class.java)
        startActivity(intent)
    }
    private fun callDescuento(){
        val intent = Intent(this, DescuentoActivity::class.java)
        startActivity(intent)
    }
    private fun callSobreNosotros(){
        val intent = Intent(this, SobreNosotrosActivity::class.java)
        startActivity(intent)
    }
}
