package com.example.uandi

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso

class CreateMaleProfile : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var profileName: EditText
    private lateinit var profileDob: EditText
    private lateinit var profileBio: EditText
    private lateinit var profileAddress: EditText
    private lateinit var profilePhone: EditText
    private lateinit var profileEdu: EditText
    private lateinit var profileOccupation: EditText
    private lateinit var maleSubmitButton: Button

    private lateinit var downloadImageUrl: String

    private lateinit var mImageUri: Uri
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mStorage: StorageReference

    private val PICK_IMAGE_REQUEST = 1


    //test
    var user = FirebaseAuth.getInstance().currentUser
    //test
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_male_profile)

        var uid=user!!.uid


        imageView = findViewById<View>(R.id.select_male_image) as ImageView
        profileName = findViewById<View>(R.id.male_name) as EditText
        profileDob = findViewById<View>(R.id.male_dob) as EditText
        profileBio = findViewById<View>(R.id.male_description) as EditText
        profileAddress = findViewById<View>(R.id.male_address) as EditText
        profilePhone = findViewById<View>(R.id.male_phone) as EditText
        profileEdu = findViewById<View>(R.id.male_education) as EditText
        profileOccupation = findViewById<View>(R.id.male_occupation) as EditText
        maleSubmitButton = findViewById<View>(R.id.add_new_male) as Button


        mDatabase = FirebaseDatabase.getInstance().getReference("MaleProfiles")
        mStorage = FirebaseStorage.getInstance().getReference("MaleProfiles")

        maleSubmitButton.setOnClickListener(View.OnClickListener {
            //if (mUploadTask != null && mUploadTask.isInProgress) {
            //    Toast.makeText(this, "An Upload is Still in Progress", Toast.LENGTH_SHORT).show();
            // } else {
            uploadFile()
            // }
        })

        imageView.setOnClickListener(View.OnClickListener {
            openFileChooser()
        })


    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null
        ) {
            mImageUri = data.data!!
            Picasso.get().load(mImageUri).into(imageView)
        }
    }




    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }



    private fun uploadFile() {
        //test
        var uid=user!!.uid
        //
        val fileReference: StorageReference = mStorage.child(
            System.currentTimeMillis().toString() + "." + getFileExtension(mImageUri)
        )
        val mUploadTask = fileReference.putFile(mImageUri)

        mUploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
            override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {

                Toast.makeText(this@CreateMaleProfile, "Upload successful", Toast.LENGTH_LONG).show()


                val urlTask: Task<Uri> =
                    mUploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            throw task.exception!!
                        }
                        downloadImageUrl = fileReference.downloadUrl.toString()
                        fileReference.downloadUrl
                    }
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                downloadImageUrl = task.result.toString()
                                //MALE
                                var maleUpload: Male = Male(
                                    mImage =downloadImageUrl,
                                    mName = profileName.text.toString(),
                                    mBio = profileBio.text.toString(),
                                    mDob = profileDob.text.toString(),
                                    mOccupation = profileOccupation.text.toString(),
                                    mPhone = profilePhone.text.toString(),
                                    mAddress = profileAddress.text.toString(),
                                    mEdu = profileEdu.text.toString()

                                )

                                //val uploadId: String? = mDatabase.push().key
                                //mDatabase.child(uploadId!!).setValue(maleUpload)
                                val uploadId:String?=profilePhone.text.toString()
                                mDatabase.child(uploadId!!).setValue(maleUpload)
                            }
                        }



                startActivity(Intent(this@CreateMaleProfile, NavigationPage::class.java))
            }

        })
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Toast.makeText(this@CreateMaleProfile, e.message, Toast.LENGTH_SHORT).show()
                }
            })
//              .addOnProgressListener(object :
//                OnProgressListener<UploadTask.TaskSnapshot?> {
//                override fun onProgress(taskSnapshot: UploadTask.TaskSnapshot) {
//                    val progress =
//                        100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                    mProgressBar.setProgress(progress.toInt())
//                }
//              })
    }

}


