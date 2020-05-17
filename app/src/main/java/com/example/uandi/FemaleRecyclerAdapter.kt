package com.example.uandi

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class FemaleRecyclerAdapter(val fContext: Context, val fUploads: List<Female>):
    RecyclerView.Adapter<FemaleRecyclerAdapter.fDashboardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fDashboardViewHolder {
        var view = LayoutInflater.from(fContext)
            .inflate(R.layout.female_single_grid, parent, false)
        return fDashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fUploads.size
    }

    override fun onBindViewHolder(holder: fDashboardViewHolder, position: Int) {
        //val Male = mUploads[position]
        //holder.txtName.text= Male.maleName
        // holder.imgview.setImageResource(Male.maleImage)
        //holder.llContext.setOnClickListener {
        //   Toast.makeText( this, "clicked on ${holder.textViewName.text}", Toast.LENGTH_SHORT).show()
        //}
        val uploadCurrent: Female = fUploads[position]
        holder.textViewNameFemale.text = uploadCurrent.getNameF()


        holder.llcontentf.setOnClickListener{
            val context=holder.textViewNameFemale.context
            val intent = Intent(context,FemaleDetails::class.java)
            intent.putExtra("femalePhone", uploadCurrent.getPhoneF())
            context.startActivity(intent)
            //  val intent = Intent(mContext,MaleDetails::class.java)
            //intent.putExtra("malePhone", uploadCurrent.getPhone())
            // startActivity(mContext,intent, Bundle())
        }
        Picasso.get()
            .load(uploadCurrent.getImageUrlF())
            .placeholder(R.drawable.load)
            .fit()
            .centerCrop()
            .into(holder.imageViewFemale, object : Callback {
                override fun onSuccess() {
                    Log.d(ContentValues.TAG,"Success")
                }

                override fun onError(e: Exception?) {
                    Log.d(ContentValues.TAG,"error")
                }
            })
    }


    class fDashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNameFemale: TextView = view.findViewById(R.id.text_view_name_female)
        val imageViewFemale: ImageView = view.findViewById(R.id.image_view_upload_female)
        val llcontentf: LinearLayout = view.findViewById(R.id.llcontentf)
    }
}