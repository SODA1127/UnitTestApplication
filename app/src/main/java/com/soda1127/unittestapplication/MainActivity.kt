package com.soda1127.unittestapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soda1127.unittestapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

    }

}
