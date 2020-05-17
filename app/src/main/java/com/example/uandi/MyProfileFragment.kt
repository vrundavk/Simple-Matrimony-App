package com.example.uandi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_my_profile.*


/**
 * A simple [Fragment] subclass.
 */
class MyProfileFragment : Fragment() {
    lateinit var mDatabase: DatabaseReference
    lateinit var databaseReference: DatabaseReference
    lateinit var userImage: ImageView
    lateinit var userName: EditText
    lateinit var userDob: EditText
    lateinit var userPhone: EditText
    lateinit var userAddress: EditText
    lateinit var userOccupation: EditText
    lateinit var userQualification: EditText
    lateinit var userBio: EditText


    private var phoneID: String? = ""




    lateinit var update_button:TextView
    lateinit var delete_button:TextView

    lateinit var select_male_option:TextView
    lateinit var select_female_option:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        phoneID = activity!!.intent.getStringExtra("Phone")



        userImage = view.findViewById(R.id.settings_profile_image) as ImageView
        userName = view.findViewById(R.id.settings_full_name) as EditText
        userDob = view.findViewById(R.id.settings_dob) as EditText
        userPhone = view.findViewById(R.id.settings_phone_number) as EditText
        userAddress = view.findViewById(R.id.settings_address) as EditText
        userOccupation = view.findViewById(R.id.settings_occupation) as EditText
        userQualification = view.findViewById(R.id.settings_qualification) as EditText
        userBio = view.findViewById(R.id.settings_bio) as EditText

        getNameDetails(phoneID!!)


        //
        update_button=view.findViewById(R.id.update_button) as TextView
        delete_button=view.findViewById(R.id.delete_button) as TextView

        select_male_option=view.findViewById(R.id.select_male_option)
        select_female_option=view.findViewById(R.id.select_female_option)
        //
        select_male_option.setOnClickListener {
            val intent = Intent(activity,CreateMaleProfile::class.java)
            startActivity(intent)
        }
        select_female_option.setOnClickListener {
            val intent = Intent(activity,CreateFemaleProfile::class.java)
            startActivity(intent)
        }


update_button.setOnClickListener{
    update(phoneID!!)
}
delete_button.setOnClickListener { 
    delete(phoneID!!)
}






        return view
    }

    private fun update(phoneID: String) {

        val databaseReference = FirebaseDatabase.getInstance().getReference("MaleProfiles")
        databaseReference.child(phoneID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                if (dataSnapshot.exists()) {
//                    val males: Male? = dataSnapshot.getValue(Male::class.java)

                   Toast.makeText(activity, "successfully updated", Toast.LENGTH_SHORT).show()
//                    if (males != null) {
//                        userName.setText(males.getName())
//                        userDob.setText(males.getDob())
//                        userBio.setText(males.getBio())
//                        userAddress.setText(males.getAddress())
//                        userPhone.setText(males.getPhone())
//                        userQualification.setText(males.getEdu())
//                        userOccupation.setText(males.getOccupation())
//                        Picasso.get().load(males.getImageUrl()).into(userImage)
//                    }
//
//                }
                dataSnapshot.ref.child("name").setValue(userName.text.toString())
                dataSnapshot.ref.child("dob").setValue(userDob.text.toString())
                dataSnapshot.ref.child("bio").setValue(userBio.text.toString())
                dataSnapshot.ref.child("address").setValue(userAddress.text.toString())
                dataSnapshot.ref.child("phone").setValue(userPhone.text.toString())
                dataSnapshot.ref.child("edu").setValue(userQualification.text.toString())
                dataSnapshot.ref.child("occupation").setValue(userOccupation.text.toString())


            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(activity, "error updating", Toast.LENGTH_SHORT).show()
            }


        })



        val fdatabaseReference = FirebaseDatabase.getInstance().getReference("FemaleProfiles")
        fdatabaseReference.child(phoneID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                if (dataSnapshot.exists()) {
//                    val males: Male? = dataSnapshot.getValue(Male::class.java)

                Toast.makeText(activity, "successfully updated", Toast.LENGTH_SHORT).show()
//                    if (males != null) {
//                        userName.setText(males.getName())
//                        userDob.setText(males.getDob())
//                        userBio.setText(males.getBio())
//                        userAddress.setText(males.getAddress())
//                        userPhone.setText(males.getPhone())
//                        userQualification.setText(males.getEdu())
//                        userOccupation.setText(males.getOccupation())
//                        Picasso.get().load(males.getImageUrl()).into(userImage)
//                    }
//
//                }
                dataSnapshot.ref.child("nameF").setValue(userName.text.toString())
                dataSnapshot.ref.child("dobF").setValue(userDob.text.toString())
                dataSnapshot.ref.child("bioF").setValue(userBio.text.toString())
                dataSnapshot.ref.child("addressF").setValue(userAddress.text.toString())
                dataSnapshot.ref.child("phoneF").setValue(userPhone.text.toString())
                dataSnapshot.ref.child("eduF").setValue(userQualification.text.toString())
                dataSnapshot.ref.child("occupationF").setValue(userOccupation.text.toString())


            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(activity, "error updating", Toast.LENGTH_SHORT).show()
            }


        })
    }
    private fun delete(phoneID: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("MaleProfiles")
        databaseReference.child(phoneID).removeValue().addOnCompleteListener(
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "successfully deleted", Toast.LENGTH_SHORT).show()
                    val intent= Intent(activity,NavigationPage::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(activity, "error deleting", Toast.LENGTH_SHORT).show()
                }

        })
        val fdatabaseReference = FirebaseDatabase.getInstance().getReference("FemaleProfiles")
        fdatabaseReference.child(phoneID).removeValue().addOnCompleteListener(
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "successfully deleted", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(activity, "error deleting", Toast.LENGTH_SHORT).show()
                }

            })



    }
    private fun getNameDetails(phoneID: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("MaleProfiles")
        databaseReference.child(phoneID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val males: Male? = dataSnapshot.getValue(Male::class.java)

                    Toast.makeText(activity, "successfully displaying", Toast.LENGTH_SHORT).show()
                    if (males != null) {
                        userName.setText(males.getName())
                        userDob.setText(males.getDob())
                        userBio.setText(males.getBio())
                        userAddress.setText(males.getAddress())
                        userPhone.setText(males.getPhone())
                        userQualification.setText(males.getEdu())
                        userOccupation.setText(males.getOccupation())
                        Picasso.get().load(males.getImageUrl()).into(userImage)
                    }

                }

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(activity, "error displaying", Toast.LENGTH_SHORT).show()
            }


        })

        val fdatabaseReference = FirebaseDatabase.getInstance().getReference("FemaleProfiles")
        fdatabaseReference.child(phoneID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {


                    Toast.makeText(activity, "successfully displaying", Toast.LENGTH_SHORT).show()
                    val females: Female? = dataSnapshot.getValue(Female::class.java)
                    Toast.makeText(activity, "successfully displaying", Toast.LENGTH_SHORT).show()
                    if (females != null) {
                        userName.setText(females.getNameF())
                        userDob.setText(females.getDobF())
                        userBio.setText(females.getBioF())
                        userAddress.setText(females.getAddressF())
                        userPhone.setText(females.getPhoneF())
                        userQualification.setText(females.getEduF())
                        userOccupation.setText(females.getOccupationF())
                        Picasso.get().load(females.getImageUrlF()).into(userImage)
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(activity, "error displaying", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
