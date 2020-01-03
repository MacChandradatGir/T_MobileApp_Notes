package com.example.t_mobileapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.t_mobileapp.R
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.databinding.ActivityMainBinding
import com.example.t_mobileapp.model.Item
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.model.UserBio
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

    }


    private fun displayUsers(users: ArrayList<UserBio>) {
        val adapter = UserAdapter(users)
        main_recyclerview.adapter = adapter
        val linear = LinearLayoutManager(this)
        main_recyclerview.layoutManager = linear

    }

//    private fun makeApiCall(searchEditText: String) {
//       // var searchEditText = search_edittext.text.toString().trim()
//
//        compositeDisposable.add(
//            viewModel.getUsers("${searchEditText}")
//                .subscribe({user ->
//                    displayUsers(user.items)
//                }, {throwable ->
//                    Log.e("TAG_ERROR", throwable.toString())
//                })
//
//        )
//    }




}
