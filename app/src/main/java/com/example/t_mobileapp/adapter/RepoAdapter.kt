package com.example.t_mobileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t_mobileapp.R
import com.example.t_mobileapp.model.Repository

class RepoAdapter(var repoList: List<Repository>):
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item_layout, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoAdapter.RepoViewHolder, position: Int) {
        holder.repoNameTextView.text = repoList[position].name
        holder.startextView.text = repoList[position].stargazersCount.toString()
        holder.forkTextView.text = repoList[position].forksCount.toString()

    }

    fun updateList(updateList : List<Repository>){
        repoList = updateList
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val repoNameTextView: TextView = itemView.findViewById(R.id.repo_name_textview)
        val forkTextView: TextView = itemView.findViewById(R.id.fork_textview)
        val startextView: TextView = itemView.findViewById(R.id.star_textview)

    }
}