package com.example.uandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile_option.*

class ProfileOption : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    val mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    //progressBar
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option)


        val disText=findViewById<View>(R.id.dispTxt) as TextView
        var uid=user!!.uid

        mDatabase= FirebaseDatabase.getInstance().getReference("Names")

        //progressBar
        progressLayout=findViewById(R.id.progressLayout)
        progressBar=findViewById(R.id.progressBar)
        progressLayout.visibility= View.VISIBLE
        //

        mDatabase.child(uid).child("Name").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //
                progressLayout.visibility= View.GONE
                //
                val result = snapshot.value.toString()
                disText.text = "Welcome "+ result+" :)"
            }
        })
        textView10.setOnClickListener {
            val intent = Intent(this,CreateMaleProfile::class.java)
            startActivity(intent)
        }
        textView11.setOnClickListener {
            val intent = Intent(this,CreateFemaleProfile::class.java)
            startActivity(intent)
        }


    }
    override fun onPause() {
        super.onPause()
        finish()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menuoptions,menu)
//        return super.onCreateOptionsMenu(menu)
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId== R.id.SignOut) {
//            mAuth.signOut()
//            startActivity(Intent(this, MainActivity::class.java))
//            Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}


