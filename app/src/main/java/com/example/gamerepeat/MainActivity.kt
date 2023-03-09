package com.example.gamerepeat

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamerepeat.fragments.GameFragment
import com.example.gamerepeat.fragments.WebFragment

class MainActivity : AppCompatActivity() {
    //private val webView = findViewById<WebView>(R.id.webView)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState != null) {
//            webView.restoreState(savedInstanceState)
//        }else {
//            webView.loadUrl("https://ohmytraff.space/api")
//        }

        isOnline(this)

    }
    private fun isOnline(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnectedOrConnecting) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, WebFragment())
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, GameFragment())
                .commit()
        }
    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        webView.saveState(outState)
//        super.onSaveInstanceState(outState)
//    }
}