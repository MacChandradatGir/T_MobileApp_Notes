package com.example.t_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t_mobileapp.R
import com.example.t_mobileapp.model.Repository
import kotlinx.android.synthetic.main.repo_item_layout.view.*

class RepoAdapter(var repoList: List<Repository>)
    : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(LayoutInflater
        .from(parent.context)
        .inflate(R.layout.repo_item_layout, parent, false))


    override fun getItemCount(): Int = repoList.size


    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int)
        = holder.bindRepository(repoList[position])

    fun updateList(updateList : List<Repository>){
        repoList = updateList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindRepository(repository: Repository){
            itemView.repo_name_textview.text = repository.name
            itemView.fork_textview.text = "${repository.forksCount}"
            itemView.star_textview.text = "${repository.stargazersCount}"
        }

    }
}

