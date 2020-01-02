package com.example.t_mobileapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.t_mobileapp.R
import com.example.t_mobileapp.model.Item
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.viewmodel.GitViewModel


class UserAdapter(val userList: List<Item>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }



    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(userList[position].avatarUrl)
            .into(holder.userAvatar)

        holder.username.text = userList[position].login.toString()


        holder.username.setOnClickListener{ view ->

            var userSelected = userList[position].login.toString()
            Toast.makeText(view.context, "${userSelected}", Toast.LENGTH_SHORT).show()
//            var intent: Intent = Intent(view.context, GitViewModel::class.java)
//            intent.putExtra("USERSELECTED", userSelected)
//            view.context.startActivity(intent)

        }


    }

    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val userAvatar: ImageView = itemView.findViewById(R.id.user_imageview)
        val username: TextView = itemView.findViewById(R.id.user_name_textview)
       // val reponame: TextView = itemView.findViewById(R.id.repo_name_textview)


    }

}