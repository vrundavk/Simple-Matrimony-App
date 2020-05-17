package com.example.uandi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    val mAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

//        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name),
//            Context.MODE_PRIVATE)
//
//        val isLoggedIn=sharedPreferences.getBoolean("isLoggedIn",false)

        setContentView(R.layout.activity_main)

//        if(isLoggedIn){
//            val intent=Intent(this@MainActivity,NavigationPage::class.java)
//            finish()
//            startActivity(intent)
//        }

        val loginBtn = findViewById<View>(R.id.loginBtn) as Button

        loginBtn.setOnClickListener(View.OnClickListener {
                view -> login()
        })

        val registerBtn = findViewById<View>(R.id.registerBtn) as Button

        registerBtn.setOnClickListener{
            val intent = Intent(this,Registration::class.java)
            startActivity(intent)
        }

    }
    private fun login() {
        val emailTxt = findViewById<View>(R.id.login_email_input) as EditText
        val passwordTxt = findViewById<View>(R.id.login_password_input) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( this, OnCompleteListener { task ->
                if (task.isSuccessful) {

                    //savePreferences()
                    startActivity(Intent(this, NavigationPage::class.java))
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()

                } else {
                   // savePreferences()
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

                }

            })
        } else {
            Toast.makeText( this, "please fill up the credentials", Toast.LENGTH_LONG).show()
        }



    }
//
//    fun savePreferences() {
//        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
//    }
}
