package com.miss.framebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miss.framebase.databinding.ActivityMainBinding
import com.miss.lib_common.common.ScreenShot
import com.miss.lib_common.common.ScreenUtil

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tv.setOnClickListener {
            binding.ivTest.setImageBitmap(ScreenShot.screenShotView(binding.tv))
        }


    }
}