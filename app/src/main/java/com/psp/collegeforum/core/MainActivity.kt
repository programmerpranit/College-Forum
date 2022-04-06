package com.psp.collegeforum.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController




    }

    //TODO: Add this function in view model
    //FUNCTION TO CHANGE COLOUR OF TEXT OF USER
    /**
    fun Setcolor(){
        var x =3
        when (x) {
            1 -> tvName.setTextColor(getResources().getColor(R.color.clr1))
            2 -> tvName.setTextColor(getResources().getColor(R.color.clr2))
            3 -> tvName.setTextColor(getResources().getColor(R.color.clr3))
            4 -> tvName.setTextColor(getResources().getColor(R.color.clr4))
        }

    }*/

}