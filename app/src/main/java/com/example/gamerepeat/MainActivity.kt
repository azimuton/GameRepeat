package com.example.gamerepeat

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gamerepeat.databinding.ActivityMainBinding
import com.example.gamerepeat.fragments.GameFragment
import com.example.gamerepeat.fragments.WebFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.webView.apply {
//
//            webViewClient = (object  : WebViewClient(){
//                override fun onReceivedHttpError(
//                    view: WebView?,
//                    request: WebResourceRequest?,
//                    errorResponse: WebResourceResponse?
//                ) {
//                    super.onReceivedHttpError(view, request, errorResponse)
//                    if (request?.url != null)
//                        if (request.url.toString() == "https://ohmytraff.space/api" && errorResponse?.statusCode == 404) {
//                            binding.flMain.visibility = View.VISIBLE
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(R.id.flMain, GameFragment())
//                                .commit()
//                        }
//                }
//            })
//
//            if (savedInstanceState != null)
//                restoreState(savedInstanceState);
//            else
//                loadUrl("https://ohmytraff.space/api")
//
//            settings.apply {
//                javaScriptEnabled = true
//                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//                setSupportZoom(true)
//                textZoom = 100
//                loadsImagesAutomatically = true
//            }
//        }.canGoBack()

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
//            binding.flMain.visibility = View.VISIBLE
//            binding.webView.visibility = View.GONE
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, GameFragment())
                .commit()
        }
    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        binding.webView.saveState(outState)
//        super.onSaveInstanceState(outState)
//    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        if (binding.webView.canGoBack()) {
//            binding.webView.goBack()
//        }
//    }
}