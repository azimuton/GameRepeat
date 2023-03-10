package com.example.gamerepeat

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gamerepeat.databinding.ActivityMainBinding
import com.example.gamerepeat.fragments.GameFragment

private const val KEY = "222"

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val savedRestoreStateUrl = sharedPref.getString(KEY, "default")
        if(savedRestoreStateUrl != "default") {
            binding.webView.loadUrl(savedRestoreStateUrl.toString())
        } else {
            binding.webView.loadUrl("https://ohmytraff.space/api")
        }

        binding.webView.apply {
            webViewClient = (object  : WebViewClient(){
                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    if (request?.url != null)
                        if (request.url.toString() == "https://ohmytraff.space/api" &&
                            errorResponse?.statusCode == 404) {
                            binding.flMain.visibility = View.VISIBLE
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.flMain, GameFragment())
                                .commit()
                        }
                }
            })
            settings.apply {
                javaScriptEnabled = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setSupportZoom(true)
                textZoom = 100
                loadsImagesAutomatically = true
            }
        }.canGoBack()
        isOnline(this)
    }
    private fun isOnline(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnectedOrConnecting) {

        } else {
            binding.flMain.visibility = View.VISIBLE
            binding.webView.visibility = View.GONE
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, GameFragment())
                .commit()
        }
    }
    private fun save(){
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor =sharedPref.edit()
        editor.putString(KEY, binding.webView.url)
        editor.apply()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        save()
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
            finishAffinity()
        }
    }
}