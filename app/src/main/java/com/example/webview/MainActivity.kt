package com.example.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val adsText = findViewById<EditText>(R.id.adsText)
        val btnLoad = findViewById<Button>(R.id.btnLoad)
        val adsweb = findViewById<WebView>(R.id.adsweb)
        val tvError = findViewById<TextView>(R.id.tvError)

        adsweb.apply {
            settings.apply {
                javaScriptEnabled = true
                useWideViewPort = true
            }
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    Log.e("WebView", consoleMessage?.message() ?: "")
                    tvError.text = consoleMessage?.message() ?: ""
                    return super.onConsoleMessage(consoleMessage)
                }
            }

            btnLoad.setOnClickListener {
                adsweb.loadData(adsText.text.toString(), "text/html; charset=UTF-8;", "UTF-8")
            }


        }
    }
}