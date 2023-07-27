package com.alejandro.evaluacioncontinua03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.alejandro.evaluacioncontinua03.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tilCorreo.editText?.addTextChangedListener { text ->
            binding.btnIngresar.isEnabled = validacionEmailPassword(text.toString(), binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnIngresar.isEnabled = validacionEmailPassword(binding.tilCorreo.editText?.text.toString(),text.toString(),)
        }

        binding.btnIngresar.setOnClickListener {
            Toast.makeText(applicationContext, "Bienvenido a AmiibOS!!", Toast.LENGTH_LONG).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun validacionEmailPassword(email: String,password: String):Boolean{
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email=="ejemplo@idat.edu.pe"
        val isPasswordValid = password.length >=6 && password=="123456"
        return isEmailValid && isPasswordValid
    }
}