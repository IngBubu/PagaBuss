package com.example.pruebas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.PagaBus.ValidateEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.properties.Delegates

class LoginActivity : AppCompatActivity() {

    companion object {
        lateinit var userEmail: String
        lateinit var provedorDeSesion: String
    }

    private var email by Delegates.notNull<String>()
    private var contraseña by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etContraseña: EditText
    private lateinit var lyTerminos: LinearLayout

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lyTerminos = findViewById(R.id.lyTerms)
        lyTerminos.visibility = View.INVISIBLE

        etEmail = findViewById(R.id.etEmail)
        etContraseña = findViewById(R.id.etPassword)
        mAuth = FirebaseAuth.getInstance()

        manageButtonLogin()
        etEmail.doOnTextChanged { text, start, before, count -> manageButtonLogin() }
        etContraseña.doOnTextChanged { text, start, before, count -> manageButtonLogin() }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) goHome(currentUser.email.toString(), currentUser.providerId)
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }
    private fun manageButtonLogin() {
        var tvLogin = findViewById<TextView>(R.id.tvIniciarSesion)
        email = etEmail.text.toString()
        contraseña = etContraseña.text.toString()

        if (TextUtils.isEmpty(contraseña) || ValidateEmail.isEmail(email) == false) {

            tvLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.azulusado))
            tvLogin.isEnabled = false
        } else {
            tvLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.azulusado))
            tvLogin.isEnabled = true
        }
    }

    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {
        email = etEmail.text.toString()
        contraseña = etContraseña.text.toString()

        mAuth.signInWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) goHome(email, "email")
                else {
                    if (lyTerminos.visibility == View.INVISIBLE)
                        lyTerminos.visibility = View.VISIBLE
                    else {
                        var cbAcept = findViewById<CheckBox>(R.id.cbAcepto)
                        if (cbAcept.isChecked) register()
                    }
                }
            }
    }

    private fun goHome(email: String, provider: String) {
        userEmail = email
        provedorDeSesion = provider

        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun register() {
        email = etEmail.text.toString()
        contraseña = etContraseña.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    var dataRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                    var dbRegister = FirebaseFirestore.getInstance()

                    dbRegister.collection("users").document(email).set(
                        hashMapOf(
                            "userId" to userId,
                            "user" to email,
                            "dataRegister" to dataRegister
                        )
                    )
                    goHome(email, "email")
                } else Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
    }

    fun goTerms(v: View) {
        val intent = Intent(this, TerminosActivity::class.java)
        startActivity(intent)
    }

    fun forgotPassword(view: View) {
        resetPassword()
    }

    private fun resetPassword() {
        var e = etEmail.text.toString()
        if (!TextUtils.isEmpty(e)) {
            mAuth.sendPasswordResetEmail(e).addOnCompleteListener { task ->
                if (task.isSuccessful) Toast.makeText(this, "Correo enviado a $e", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this, "Error, algo salio mal", Toast.LENGTH_SHORT).show()
            }
        } else Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_SHORT).show()
    }
}