package com.example.appautenticator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val btnLogar = findViewById<Button>(R.id.btnLogar)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnLogar.setOnClickListener {
            handleLogin()
        }

        btnCadastrar.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleLogin() {
        try {
            val emailInput = findViewById<EditText>(R.id.edtLogin)
            val passwordInput = findViewById<EditText>(R.id.edtSenha)

            val email: String = emailInput.text.toString()
            val password: String = passwordInput.text.toString()

            if(emailInput.text.isEmpty() || passwordInput.text.isEmpty()){
                throw Exception("Preencha os campos corretamente")
            }


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, PrincipalActivity::class.java)
                        startActivity(intent)
                    } else {
                        throw Exception("Falha no login")
                    }
                }


        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }
}