package com.example.appbase.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.appbase.R

class NoShipieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_shipie)

        AlertDialog.Builder(this)
            .setTitle("Aaa")
            .create()
            .show();


    }
}
