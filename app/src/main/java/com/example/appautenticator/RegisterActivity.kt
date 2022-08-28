package com.example.appautenticator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener{
            handleRegister()
        }
    }

    private fun handleRegister() {
        try {
            val emailInput = findViewById<EditText>(R.id.edtLogin)
            val passwordInput = findViewById<EditText>(R.id.edtSenha)

            val email: String = emailInput.text.toString()
            val password: String = passwordInput.text.toString()

            if(emailInput.text.isEmpty() || passwordInput.text.isEmpty()){
                throw Exception("Preencha os campos corretamente")
            }


            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(){
                    task ->
                        if(task.isSuccessful){
                            val intent = Intent(this, PrincipalActivity::class.java)
                            startActivity(intent)
                        } else {
                            throw Exception("Falha ao cadastrar")
                        }

                }


        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }
}