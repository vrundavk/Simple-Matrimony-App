package com.example.uandi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class FemaleProfilesFragment : Fragment() {
    lateinit var recyclerDashboardFemale: RecyclerView
    lateinit var layoutManagerFemale: RecyclerView.LayoutManager
    lateinit var recyclerAdapterFemale: FemaleRecyclerAdapter
    lateinit var fUploads :List<Female>


    //lateinit var mImageUri: Uri
    lateinit var fDatabaseRef: DatabaseReference
    //lateinit var mStorageRef: StorageReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_female_profiles, container, false)

        fUploads = ArrayList()

        recyclerDashboardFemale=view.findViewById(R.id.recycler_view_female)
        layoutManagerFemale= GridLayoutManager(activity,2)
        recyclerAdapterFemale= FemaleRecyclerAdapter(activity as Context, fUploads)
        fDatabaseRef= FirebaseDatabase.getInstance().getReference("FemaleProfiles")

        recyclerDashboardFemale.adapter=recyclerAdapterFemale
        recyclerDashboardFemale.layoutManager=layoutManagerFemale

//        recyclerDashboardFemale.addItemDecoration(
//            DividerItemDecoration(
//                recyclerDashboardFemale.context,
//                (layoutManagerFemale as LinearLayoutManager).orientation
//            )
//        )


        fDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val fupload: Female? = postSnapshot.getValue(Female::class.java)
                    if (fupload != null) {
                        (fUploads as ArrayList<Female>).add(fupload)
                    }
                }
                recyclerAdapterFemale = FemaleRecyclerAdapter(activity as Context, fUploads)
                recyclerDashboardFemale.setAdapter(recyclerAdapterFemale)
                //mProgressCircle.setVisibility(View.INVISIBLE)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(activity as Context, databaseError.message, Toast.LENGTH_SHORT)
                    .show()
                //mProgressCircle.setVisibility(View.INVISIBLE)
            }
        })



        return view
    }

}
