package com.deazzle.modules.covidstatmodule.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.deazzle.R

class UserDataActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupViews()

    }

    fun setupViews() {
        navController = Navigation.findNavController(this, R.id.fragNavHost)
    }
}