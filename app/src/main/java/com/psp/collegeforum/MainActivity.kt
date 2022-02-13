package com.psp.collegeforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_question.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
        //Setcolor()
    }

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