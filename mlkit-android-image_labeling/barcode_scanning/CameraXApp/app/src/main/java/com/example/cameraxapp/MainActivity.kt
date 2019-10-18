package com.example.cameraxapp

import androidx.appcompat.app.AppCompatActivity

import android.Manifest
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSIONS = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
