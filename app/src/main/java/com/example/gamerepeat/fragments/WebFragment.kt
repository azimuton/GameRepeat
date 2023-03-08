package com.example.gamerepeat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.gamerepeat.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private lateinit var binding : FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient = WebViewClient()

        // this will load the url of the website
        binding.webView.loadUrl("https://ohmytraff.space/api")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }
//    override fun onBackPressed() {
////        // if your webview can go back it will go back
////        if (binding.webView.canGoBack()) {
////            binding.webView.goBack()
////            // if your webview cannot go back
////            // it will exit the application
////        } else {
////            super.onBackPressed()
////        }
////    }

}