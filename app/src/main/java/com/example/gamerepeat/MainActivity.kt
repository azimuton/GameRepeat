package com.example.gamerepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamerepeat.fragments.GameFragment
import com.example.gamerepeat.fragments.WebFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.flMain, GameFragment())
//            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flMain, WebFragment())
            .commit()
    }
}