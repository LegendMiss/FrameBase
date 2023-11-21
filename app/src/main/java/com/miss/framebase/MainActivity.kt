package com.miss.framebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miss.lib_common.common.ScreenUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScreenUtil.getScreenProperty()
    }
}