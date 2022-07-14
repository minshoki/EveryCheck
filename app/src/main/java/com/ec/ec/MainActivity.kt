package com.ec.ec

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.ec.ec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var instance: MainActivity? = null
        const val PUSH_BASE_URL = "http://sqrt5.iptime.org:8082"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        instance = this
        initWebView()
        binding.webview.loadUrl(PUSH_BASE_URL)
    }

    override fun onDestroy() {
        instance = null
        super.onDestroy()
    }

    private fun initWebView() {
        with(binding.webview.settings) {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true

            setSupportMultipleWindows(true)
            builtInZoomControls = false
            displayZoomControls = false
            useWideViewPort = true
            loadWithOverviewMode = true
            allowFileAccess = true
            allowContentAccess = true
        }
        binding.webview.webViewClient = WebViewClient()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                binding.webview.onRequestPermissionResult()
            }
        }
    }
}