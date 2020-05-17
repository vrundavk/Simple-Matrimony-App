package com.example.uandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.nav_header.*


class NavigationPage : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {
    //test
    lateinit var mDatabase : DatabaseReference
   // val mAuth = FirebaseAuth.getInstance()
   private var user = FirebaseAuth.getInstance().currentUser
   // lateinit var navName:TextView


    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView


    lateinit var homeFragment: HomeFragment
    lateinit var myProfileFragment:MyProfileFragment
    lateinit var femaleProfilesFragment: FemaleProfilesFragment
    lateinit var maleProfilesFragment: MaleProfilesFragment
    lateinit var logoutFragment: LogoutFragment

    var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_page)



        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
       navView = findViewById(R.id.nav_view)

        val hView: View = navView.inflateHeaderView(R.layout.nav_header)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //test

        var uid=user!!.uid

        val navName=hView.findViewById<View>(R.id.navi_name) as TextView
        val navPhone =hView.findViewById<View>(R.id.navi_phone) as TextView
        mDatabase= FirebaseDatabase.getInstance().getReference("Names")

        mDatabase.child(uid).child("Name").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                 val result = snapshot.value.toString()
                //navName.text = "Welcome "+ result+" :)"
                 navName.text =result

                //navName.text=snapshot.value.toString()

            }
        })

        mDatabase.child(uid).child("Phone").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                 val PhoneResult = snapshot.value.toString()
               navPhone.text=PhoneResult
            }
        })
        //

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        navView.setCheckedItem(R.id.nav_home)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(previousMenuItem!=null){
            previousMenuItem?.isChecked=false
        }
        item.isCheckable=true
        item.isChecked=true
         previousMenuItem=item

        when (item.itemId) {
            R.id.nav_home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                supportActionBar?.title="Home"
                navView.setCheckedItem(R.id.nav_home)
                Toast.makeText(this, "Home Page :)", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_profile -> {
                myProfileFragment = MyProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,myProfileFragment)

                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                 intent.putExtra("Phone",navi_phone.text)
                supportActionBar?.title="My Profile"
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_female_profiles -> {
                Toast.makeText(this, "Viewing Female Profiles", Toast.LENGTH_SHORT).show()
                femaleProfilesFragment = FemaleProfilesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,femaleProfilesFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                supportActionBar?.title="Female Profiles"
            }
            R.id.nav_male_profiles -> {
                Toast.makeText(this, "Viewing Male Profiles", Toast.LENGTH_SHORT).show()
                maleProfilesFragment = MaleProfilesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,maleProfilesFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                supportActionBar?.title="Male Profiles"
            }
//            R.id.nav_update -> {
//                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
//            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
                logoutFragment = LogoutFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,logoutFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                supportActionBar?.title="Logout"
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

}
