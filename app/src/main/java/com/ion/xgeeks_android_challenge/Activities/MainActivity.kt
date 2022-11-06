package com.ion.xgeeks_android_challenge.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ion.xgeeks_android_challenge.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Ki labs"
        }

        var tags = arrayListOf<String>()
        tags.addAll(listOf("Kittens", "Dogs"))

    }
}