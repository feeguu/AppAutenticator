package com.example.appautenticator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        auth = Firebase.auth

        val user = auth.currentUser

        val btnDeslogar = findViewById<Button>(R.id.btnDeslogar)
        val lblEmail = findViewById<TextView>(R.id.lblEmail)

        lblEmail.text = "Você está logado como: " + user?.email

        btnDeslogar.setOnClickListener{
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}