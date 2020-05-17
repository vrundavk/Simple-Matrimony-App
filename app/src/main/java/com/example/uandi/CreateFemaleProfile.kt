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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso

class CreateFemaleProfile : AppCompatActivity() {

    private lateinit var femaleimageView: ImageView
    private lateinit var femaleprofileName: EditText
    private lateinit var femaleprofileDob: EditText
    private lateinit var femaleprofileBio: EditText
    private lateinit var femaleprofileAddress: EditText
    private lateinit var femaleprofilePhone: EditText
    private lateinit var femaleprofileEdu: EditText
    private lateinit var femaleprofileOccupation: EditText
    private lateinit var femaleSubmitButton: Button

    private lateinit var downloadImageUrl: String

    private lateinit var fImageUri: Uri
    private lateinit var fDatabase: DatabaseReference
    private lateinit var fStorage: StorageReference

    //  private lateinit var mUploadTask: StorageTask<UploadTask.TaskSnapshot>

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_female_profile)

        femaleimageView = findViewById<View>(R.id.select_female_image) as ImageView
        femaleprofileName = findViewById<View>(R.id.female_name) as EditText
        femaleprofileDob = findViewById<View>(R.id.female_dob) as EditText
        femaleprofileBio = findViewById<View>(R.id.female_description) as EditText
        femaleprofileAddress = findViewById<View>(R.id.female_address) as EditText
        femaleprofilePhone = findViewById<View>(R.id.female_phone) as EditText
        femaleprofileEdu = findViewById<View>(R.id.female_education) as EditText
        femaleprofileOccupation = findViewById<View>(R.id.female_occupation) as EditText
        femaleSubmitButton = findViewById<View>(R.id.add_new_female) as Button


        fDatabase = FirebaseDatabase.getInstance().getReference("FemaleProfiles")
        fStorage = FirebaseStorage.getInstance().getReference("FemaleProfiles")

        femaleSubmitButton.setOnClickListener(View.OnClickListener {
            //if (mUploadTask != null && mUploadTask.isInProgress) {
            //    Toast.makeText(this, "An Upload is Still in Progress", Toast.LENGTH_SHORT).show();
            // } else {
            uploadFile()
            // }
        })

        femaleimageView.setOnClickListener(View.OnClickListener {
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
            fImageUri = data.data!!
            Picasso.get().load(fImageUri).into(femaleimageView)
        }
    }


    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }


    private fun uploadFile() {
        val fileReference: StorageReference = fStorage.child(
            System.currentTimeMillis().toString() + "." + getFileExtension(fImageUri)
        )
        val mUploadTask = fileReference.putFile(fImageUri)

        mUploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
            override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot?) {

                Toast.makeText(this@CreateFemaleProfile, "Upload successful", Toast.LENGTH_LONG)
                    .show()


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
                                var femaleUpload: Female = Female(
                                    //mImage =  taskSnapshot.downloadUrl.result.toString(),
                                    // mImage = taskSnapshot!!.getMetadata()!!.getReference()!!.getDownloadUrl(),
                                    //mImage = fileReference.getDownloadUrl().toString(),
                                    fImage = downloadImageUrl,
                                    fName = femaleprofileName.text.toString(),
                                    fBio = femaleprofileBio.text.toString(),
                                    fDob = femaleprofileDob.text.toString(),
                                    fOccupation = femaleprofileOccupation.text.toString(),
                                    fPhone = femaleprofilePhone.text.toString(),
                                    fAddress = femaleprofileAddress.text.toString(),
                                    fEdu = femaleprofileEdu.text.toString()
                                )

                                //val uploadId: String? = fDatabase.push().key
                                //fDatabase.child(uploadId!!).setValue(femaleUpload)
                                val uploadId:String?=femaleprofilePhone.text.toString()
                                fDatabase.child(uploadId!!).setValue(femaleUpload)
                            }
                        }

//                   var maleUpload: Male = Male(
//                      //mImage =  taskSnapshot.downloadUrl.result.toString(),
//                       // mImage = taskSnapshot!!.getMetadata()!!.getReference()!!.getDownloadUrl(),
//                       mImage = fileReference.getDownloadUrl().toString(),
//                       mName = profileName.text.toString(),
//                       mDob = profileDob.text.toString(),
//                       mHeight = profileHeight.text.toString(),
//                       mAddress = profileAddress.text.toString(),
//                       mPhone = profilePhone.text.toString(),
//                       mEmail = profileEmail.text.toString(),
//                       mEdu = profileEdu.text.toString(),
//                       mOccupation = profileOccupation.text.toString()
//                   )
//
//                   val uploadId: String? = mDatabase.push().key
//                   mDatabase.child(uploadId!!).setValue(maleUpload)

                startActivity(Intent(this@CreateFemaleProfile, NavigationPage::class.java))
            }

        })
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Toast.makeText(this@CreateFemaleProfile, e.message, Toast.LENGTH_SHORT).show()
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
