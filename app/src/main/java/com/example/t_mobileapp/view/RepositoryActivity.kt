package com.example.t_mobileapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.t_mobileapp.R
import com.example.t_mobileapp.adapter.RepoAdapter
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.databinding.ActivityRepositoryBinding
import com.example.t_mobileapp.model.Repository
import com.example.t_mobileapp.model.UserBio
import com.example.t_mobileapp.network.USER_INFO_ID
import com.example.t_mobileapp.viewmodel.RepositoryViewModel
import com.example.t_mobileapp.viewmodel.RepositoryViewModelFactory
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {

    lateinit var viewBinding : ActivityRepositoryBinding
    lateinit var viewModelFactory: RepositoryViewModelFactory
    lateinit var viewModel:RepositoryViewModel
    lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_repository)
        viewModelFactory = RepositoryViewModelFactory(application, intent.extras?.getSerializable(USER_INFO_ID)!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryViewModel::class.java)
        viewBinding.viewmodel = viewModel
        viewModel.userAvatar.observe(this, Observer { avatar_url ->
            Glide
                .with(this)
                .load(avatar_url)
                .into(user_avatar_imageview)
        })
        viewModel.allRepository.observe(this, Observer { repoList -> updateRepositoryList(repoList) })
        viewModel.filteredRepositoryList.observe(this, Observer { repoList -> updateRepositoryList(repoList)})
    }

    private fun updateRepositoryList(userRepoList : List<Repository>){
        if(::adapter.isInitialized.not()){
            val linearManager = LinearLayoutManager(this)
            adapter = RepoAdapter(userRepoList)
            repo_recyclerview.layoutManager = linearManager
            repo_recyclerview.adapter = adapter
        }
        else{
            adapter.updateList(userRepoList)
        }
    }

}
