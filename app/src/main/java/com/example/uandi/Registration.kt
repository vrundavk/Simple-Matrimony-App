package com.example.uandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val regBtn = findViewById<View>(R.id.register_btn) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        regBtn.setOnClickListener(View.OnClickListener {
            register()
        })

    }

    private fun register() {
        val nameTxt = findViewById<View>(R.id.register_username_input) as EditText
        val phoneTxt = findViewById<View>(R.id.register_userphone_input) as EditText
        val emailTxt = findViewById<View>(R.id.register_email_input) as EditText
        val passwordTxt = findViewById<View>(R.id.register_password_input) as EditText

        var name = nameTxt.text.toString()
        var phone = phoneTxt.text.toString()
        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()


        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        val uid = user!!.uid
                        mDatabase.child(uid).child("Name").setValue(name)
                        mDatabase.child(uid).child("Phone").setValue(phone)


                        Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, ProfileOption::class.java))

                    } else {
                        Toast.makeText(this, "Error registering!" +
                                "", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            Toast.makeText(this, "Please enter the credentials", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
