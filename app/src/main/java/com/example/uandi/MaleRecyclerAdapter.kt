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

class MaleRecyclerAdapter(val mContext: Context, val mUploads: List<Male>):
    RecyclerView.Adapter<MaleRecyclerAdapter.DashboardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        var view = LayoutInflater.from(mContext)
            .inflate(R.layout.male_single_grid, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUploads.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        //val Male = mUploads[position]
        //holder.txtName.text= Male.maleName
//        // holder.imgview.setImageResource(Male.maleImage)
//        holder.itemView.setOnClickListener {
//            Toast.makeText( this@MaleRecyclerAdapter, "clicked on ${holder.textViewName.text}", Toast.LENGTH_SHORT).show()
//        }
        val uploadCurrent: Male = mUploads[position]
        holder.textViewName.text = uploadCurrent.getName()


        holder.llContent.setOnClickListener{
            val context=holder.textViewName.context
            val intent = Intent(context,MaleDetails::class.java)
            intent.putExtra("malePhone", uploadCurrent.getPhone())
            context.startActivity(intent)
            //  val intent = Intent(mContext,MaleDetails::class.java)
            //intent.putExtra("malePhone", uploadCurrent.getPhone())
            // startActivity(mContext,intent, Bundle())
        }

        Picasso.get()

            .load(uploadCurrent.getImageUrl())
            .placeholder(R.drawable.load)
            .fit()
            .centerCrop()
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    Log.d(ContentValues.TAG,"Success")
                }

                override fun onError(e: Exception?) {
                    Log.d(ContentValues.TAG,"error")
                }
            })


    }






    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewName: TextView = view.findViewById(R.id.text_view_name)
        val imageView: ImageView = view.findViewById(R.id.image_view_upload)
        //         val profilePhone:TextView=view.findViewById(R.id.profilePhone)
        val llContent: LinearLayout = view.findViewById(R.id.llcontent)
    }



}