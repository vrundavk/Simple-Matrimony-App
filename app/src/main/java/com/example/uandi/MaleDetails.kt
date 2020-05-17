package com.example.uandi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class MaleDetails : AppCompatActivity() {
    private lateinit var maleDetailImage: ImageView
    private lateinit var maleDetailName: EditText
    private lateinit var maleDetailDob: EditText
    private lateinit var maleDetailBio: EditText
    private lateinit var maleDetailPhone: EditText
    private lateinit var maleDetailAddress: EditText
    private lateinit var maleDetailEdu: EditText
    private lateinit var maleDetailOcc: EditText
    //private var maleID=""
    private var maleID: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_male_details)
        maleID=intent.getStringExtra("malePhone")

        maleDetailImage=findViewById(R.id.male_details_image)
        maleDetailName=findViewById(R.id.male_details_name)
        maleDetailDob=findViewById(R.id.male_details_dob)
        maleDetailBio=findViewById(R.id.male_details_description)
        maleDetailAddress=findViewById(R.id.male_details_address)
        maleDetailPhone=findViewById(R.id.male_details_phone)

        maleDetailEdu=findViewById(R.id.male_details_education)
        maleDetailOcc=findViewById(R.id.male_details_occupation)

        getMaleDetails(maleID!!)




    }

    private fun getMaleDetails(maleID:String) {
        val mDatabaseRef =
            FirebaseDatabase.getInstance().getReference("MaleProfiles")
        mDatabaseRef.child(maleID).addValueEventListener(object : ValueEventListener {
            override  fun onDataChange(dataSnapshot: DataSnapshot) {
                // for (postSnapshot in dataSnapshot.children) {
                //val males: Male? = postSnapshot.getValue(Male::class.java)
                if (dataSnapshot.exists()) {
                    val males: Male? = dataSnapshot.getValue(Male::class.java)
                    Toast.makeText(this@MaleDetails,"successfully displaying", Toast.LENGTH_SHORT).show()
                    if (males != null) {
                        maleDetailName.setText(males.getName())
                        maleDetailDob.setText(males.getDob())
                        maleDetailBio.setText(males.getBio())
                        maleDetailAddress.setText(males.getAddress())
                        maleDetailPhone.setText(males.getPhone())
                        maleDetailEdu.setText(males.getEdu())
                        maleDetailOcc.setText(males.getOccupation())
                        Picasso.get().load(males.getImageUrl()).into(maleDetailImage)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MaleDetails,"error displaying", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


