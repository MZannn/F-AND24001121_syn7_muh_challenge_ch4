package com.example.chapter4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter4.R
import com.example.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}