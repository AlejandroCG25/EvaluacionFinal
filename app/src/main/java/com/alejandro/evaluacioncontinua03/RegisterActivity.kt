package com.alejandro.evaluacioncontinua03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.alejandro.evaluacioncontinua03.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth

        binding.tilCorreo.editText?.addTextChangedListener { text ->
            binding.btnIngresar.isEnabled = validacionEmailPassword(text.toString(), binding.tilPassword.editText?.text.toString(),binding.tilPassword2.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnIngresar.isEnabled = validacionEmailPassword(binding.tilCorreo.editText?.text.toString(),text.toString(),binding.tilPassword2.editText?.text.toString())
        }
        binding.tilPassword2.editText?.addTextChangedListener { text ->
            binding.btnIngresar.isEnabled = validacionEmailPassword(binding.tilCorreo.editText?.text.toString(),text.toString(),binding.tilPassword.editText?.text.toString())
        }

        binding.btnIngresar.setOnClickListener {
            val password = binding.tilPassword.editText?.text.toString()
            val email = binding.tilCorreo.editText?.text.toString()
            signUpWithEmailAndPassword(email, password)
        }
    }

    private fun signUpWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task->
            if (task.isSuccessful){
                Toast.makeText(this,"El usuario fue registrado exitosamente",Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validacionEmailPassword(email: String,password: String, password2: String):Boolean{
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >=6
        val isPasswordValid2 = password==password2
        return isEmailValid && isPasswordValid && isPasswordValid2
    }
}
