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

class FemaleDetails : AppCompatActivity() {
    private lateinit var femaleDetailImage: ImageView
    private lateinit var femaleDetailName: EditText
    private lateinit var femaleDetailDob: EditText
    private lateinit var femaleDetailBio: EditText
    private lateinit var femaleDetailPhone: EditText
    private lateinit var femaleDetailAddress: EditText
    private lateinit var femaleDetailEdu: EditText
    private lateinit var femaleDetailOcc: EditText
    //private var maleID=""
    private var femaleID: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_female_details)
        femaleID=intent.getStringExtra("femalePhone")

        femaleDetailImage=findViewById(R.id.female_details_image)
        femaleDetailName=findViewById(R.id.female_details_name)
        femaleDetailDob=findViewById(R.id.female_details_dob)
        femaleDetailBio=findViewById(R.id.female_details_description)
        femaleDetailAddress=findViewById(R.id.female_details_address)
        femaleDetailPhone=findViewById(R.id.female_details_phone)
        femaleDetailEdu=findViewById(R.id.female_details_education)
        femaleDetailOcc=findViewById(R.id.female_details_occupation)

        getFemaleDetails(femaleID!!)




    }
    private fun getFemaleDetails(femaleID:String) {
        val fDatabaseRef =
            FirebaseDatabase.getInstance().getReference("FemaleProfiles")

        fDatabaseRef.child(femaleID).addValueEventListener(object : ValueEventListener {
            override  fun onDataChange(dataSnapshot: DataSnapshot) {
                // for (postSnapshot in dataSnapshot.children) {
                //val males: Male? = postSnapshot.getValue(Male::class.java)
                if (dataSnapshot.exists()) {
                    val females: Female? = dataSnapshot.getValue(Female::class.java)
                    Toast.makeText(this@FemaleDetails,"successfully displaying", Toast.LENGTH_SHORT).show()
                    if (females != null) {
                        femaleDetailName.setText(females.getNameF())
                        femaleDetailDob.setText(females.getDobF())
                        femaleDetailBio.setText(females.getBioF())
                        femaleDetailAddress.setText(females.getAddressF())
                        femaleDetailPhone.setText(females.getPhoneF())
                        femaleDetailEdu.setText(females.getEduF())
                        femaleDetailOcc.setText(females.getOccupationF())
                        Picasso.get().load(females.getImageUrlF()).into(femaleDetailImage)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@FemaleDetails,"error displaying", Toast.LENGTH_SHORT).show()
            }
        })

    }
}

