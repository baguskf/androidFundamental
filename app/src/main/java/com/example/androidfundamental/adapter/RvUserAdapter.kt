package com.example.androidfundamental.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfundamental.Detail.Detail
import com.example.androidfundamental.ItemsItem
import com.example.androidfundamental.R

class RvUserAdapter(private val listUser: List<ItemsItem>) : RecyclerView.Adapter<RvUserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_review, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val getUserlist = listUser[position]
        viewHolder.tvUser.text = getUserlist.login
        Glide.with(viewHolder.itemView)
            .load(getUserlist.avatarUrl)
            .into(viewHolder.ivImage)

        viewHolder.itemView.setOnClickListener {
            val goDetail = Intent(viewHolder.itemView.context, Detail::class.java)
            goDetail.putExtra("Data", getUserlist.login)
            viewHolder.itemView.context.startActivity(goDetail)
            Toast.makeText(viewHolder.itemView.context, "Kamu memilih " + listUser[viewHolder.adapterPosition].login, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = listUser.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.photo)
        val tvUser: TextView = view.findViewById(R.id.tvItem)
    }

}