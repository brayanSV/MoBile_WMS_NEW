package com.user.ingeweb.alcalist_mobile_wms_new.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.user.ingeweb.alcalist_mobile_wms_new.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}