package com.example.t_mobileapp.adapter

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.t_mobileapp.R

import com.example.t_mobileapp.model.UserBioInfo
import com.example.t_mobileapp.network.USER_INFO_ID
import com.example.t_mobileapp.view.MainActivity
import com.example.t_mobileapp.view.RepositoryActivity
import kotlinx.android.synthetic.main.activity_repository.view.*
import kotlinx.android.synthetic.main.activity_repository.view.user_name_textview
import kotlinx.android.synthetic.main.repo_item_layout.view.*
import kotlinx.android.synthetic.main.user_item_layout.view.*


class UserAdapter():
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private var userBioInfoList = ArrayList<UserBioInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }


    override fun getItemCount()= userBioInfoList.size

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int)
        = holder.bindUserInfo(userBioInfoList[position])

    fun updateUserBioList(userBioInfo : UserBioInfo){
        userBioInfoList.add(userBioInfo)
        notifyDataSetChanged()
    }

    fun clearUserBioInfoList(){
        userBioInfoList.clear()
        notifyDataSetChanged()
    }



    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindUserInfo(userBioInfo :UserBioInfo){
            itemView.user_name_textview.text = userBioInfo.login
            itemView.repo_textview.text = "Repo: ${userBioInfo.public_repos}"
            Glide
                .with(itemView)
                .load(userBioInfo.avatar_url)
                .into(itemView.user_imageview)

            itemView.setOnClickListener{
                val intent = Intent(it.context, RepositoryActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(USER_INFO_ID, userBioInfo)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }

        }

    }

}