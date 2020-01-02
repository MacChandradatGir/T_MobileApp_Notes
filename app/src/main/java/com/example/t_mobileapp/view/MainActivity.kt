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
import com.example.t_mobileapp.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GitViewModel

   // private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //We need to use the Databinding util to set the content view instead of the
        // Activities setContentView
        //setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)
        binding.viewmodel = viewModel
        //We need to observe the LiveData Object in the viewmodel for changes to the
        //  dataset.  If the Dataset changes, take action
        viewModel.userInfo.observe(this, Observer<User> { user ->
            displayUsers(user.items)
        })

        //This should be handled in the ViewModel
//        search_edittext.doOnTextChanged { text, start, count, after ->
//            var searchEditText = search_edittext.text.toString().trim()
//           viewModel.makeApiCall(searchEditText)
//        }

       // compositeDisposable

    }

    private fun displayUsers(users: List<Item>) {
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
