package com.example.uandi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_logout, container, false)
       val logoutFrag= view.findViewById<Button>(R.id.logout_frag)
        val cancelFrag= view.findViewById<Button>(R.id.cancel_frag)


            logoutFrag.setOnClickListener {
                logout()
            }

            cancelFrag.setOnClickListener {
                cancel()
            }



        return view
    }
    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        val intent= Intent(activity,MainActivity::class.java)
        startActivity(intent)
    }
    private fun cancel(){
        val intent= Intent(activity,NavigationPage::class.java)
        startActivity(intent)
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
//            startActivity(Intent(activity, MainActivity::class.java))
//            Toast.makeText(activity, "Logged out", Toast.LENGTH_LONG).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
