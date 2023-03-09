package com.example.gamerepeat.fragments

import android.annotation.SuppressLint
import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.gamerepeat.R
import com.example.gamerepeat.databinding.FragmentWebBinding

@Suppress("UNREACHABLE_CODE")
class WebFragment : Fragment() {
    private lateinit var binding: FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
//        if (savedInstanceState != null)
//            restoreState(savedInstanceState)
//        else
//            binding.webView.loadUrl("https://ohmytraff.space/api")
    }
//        override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //val webView = activity?.findViewById(R.id.webView) as WebView
//        if (savedInstanceState != null) {
//            binding.webView.restoreState(savedInstanceState)
//        }else {
//            binding.webView.loadUrl("https://ohmytraff.space/api")
//        }
//    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {

            webViewClient = (object  : WebViewClient(){
                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    if (request?.url != null)
                        if (request.url.toString() == "https://ohmytraff.space/api" && errorResponse?.statusCode == 404) {
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.flMain, GameFragment())
                        ?.commit()
                        }
                }
            })
            if (savedInstanceState != null)
                restoreState(savedInstanceState);
            else
                loadUrl("https://ohmytraff.space/api")

            settings.apply {
                javaScriptEnabled = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setSupportZoom(true)
                textZoom = 100
                loadsImagesAutomatically = true
            }
        }.canGoBack()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                }
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        binding.webView.saveState(outState)
//    }

    }
