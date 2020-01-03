package com.example.t_mobileapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.t_mobileapp.R
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.databinding.ActivityMainBinding
import com.example.t_mobileapp.model.UserBioInfo
import com.example.t_mobileapp.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding
    lateinit var viewModel: UsersViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        viewBinding.viewmodel = viewModel

        viewModel.clearList.observe(this, Observer { needsCleared ->
            if(::userAdapter.isInitialized){
                if(needsCleared){
                    userAdapter.clearUserBioInfoList()
                }
            }
        })

        viewModel.userToAdd.observe(this, Observer { userBioInfo ->
            addUserToList(userBioInfo )
        })
    }

    private fun addUserToList(userBioInfo: UserBioInfo){
        if(::userAdapter.isInitialized.not()){
            val linearManager = LinearLayoutManager(this)
            userAdapter = UserAdapter()
            main_recyclerview.layoutManager = linearManager
            main_recyclerview.adapter = userAdapter

        }
            userAdapter.updateUserBioList(userBioInfo)
    }


}
