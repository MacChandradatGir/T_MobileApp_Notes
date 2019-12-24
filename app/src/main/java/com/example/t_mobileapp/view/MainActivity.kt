package com.example.t_mobileapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.t_mobileapp.R
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.model.Item
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    private lateinit var viewModel: GitViewModel

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)

        search_button.setOnClickListener{
            makeApiCall()
        }


        compositeDisposable

    }

    private fun makeApiCall() {
        var searchEditText = search_edittext.text.toString()

        compositeDisposable.add(
            viewModel.getUsers("${searchEditText}")
                .subscribe({user ->
                    displayUsers(user.items)
                }, {throwable ->
                    Log.e("TAG_ERROR", throwable.toString())
                })

        )
    }

    private fun displayUsers(users: List<Item>) {
        val adapter = UserAdapter(users)
        main_recyclerview.adapter = adapter
        val linear = LinearLayoutManager(this)
        main_recyclerview.layoutManager = linear

    }


}