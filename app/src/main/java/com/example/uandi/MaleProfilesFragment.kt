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
class MaleProfilesFragment : Fragment() {


    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: MaleRecyclerAdapter
    lateinit var mUploads: List<Male>
    lateinit var mDBListener: ValueEventListener

    //lateinit var mImageUri: Uri
    lateinit var mDatabaseRef: DatabaseReference
    //lateinit var mStorageRef: StorageReference






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_male_profiles, container, false)


        mUploads = ArrayList()

        recyclerDashboard = view.findViewById(R.id.recycler_view)
        // layoutManager=LinearLayoutManager(this)
        layoutManager = GridLayoutManager(activity, 2)
        recyclerAdapter = MaleRecyclerAdapter(activity as Context, mUploads)
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("MaleProfiles")

        recyclerDashboard.adapter = recyclerAdapter
        recyclerDashboard.layoutManager = layoutManager

//        recyclerDashboard.addItemDecoration(
//            DividerItemDecoration(
//                recyclerDashboard.context,
//                (layoutManager as LinearLayoutManager).orientation
//            )
//        )


        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val upload: Male? = postSnapshot.getValue(Male::class.java)
                    if (upload != null) {
                        (mUploads as ArrayList<Male>).add(upload)
                    }
                }
                recyclerAdapter = MaleRecyclerAdapter(activity as Context, mUploads)
                recyclerDashboard.setAdapter(recyclerAdapter)
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
