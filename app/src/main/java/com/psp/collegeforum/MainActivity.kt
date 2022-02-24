package com.psp.collegeforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //TODO: Change To View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Setcolor()
    }

    //TODO: Add this function in view model
    //FUNCTION TO CHANGE COLOUR OF TEXT OF USER
    /*
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