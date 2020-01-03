package com.example.t_mobileapp.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.t_mobileapp.R
import com.example.t_mobileapp.model.UserBio
import com.example.t_mobileapp.network.USER_INFO_ID
import com.example.t_mobileapp.view.RepositoryActivity
import kotlinx.android.synthetic.main.activity_repository.view.*
import kotlinx.android.synthetic.main.repo_item_layout.view.*


class UserAdapter(val userBioList: ArrayList<UserBio>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }


    override fun getItemCount()= userBioList.size

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int)
        = holder.bindUserInfo(userBioList[position])

    fun updateUserBioList(userBio : UserBio){
        userBioList.add(userBio)
        notifyDataSetChanged()
    }

    fun clearUserBioList(){
        userBioList.clear()
        notifyDataSetChanged()
    }



    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindUserInfo(userBio: UserBio){
            itemView.user_name_textview.text = userBio.login
            itemView.repo_name_textview.text = "Repo: ${userBio.publicRepos}"
            Glide
                .with(itemView)
                .load(userBio.avatarUrl)
                .into(itemView.user_avatar_imageview)

            itemView.setOnClickListener{
                val intent = Intent(it.context, RepositoryActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable(USER_INFO_ID, userBio)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }

        }

    }

}