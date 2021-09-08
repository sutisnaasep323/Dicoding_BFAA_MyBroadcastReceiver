package com.example.mybroadcastreceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mybroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        binding?.btnPermission?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}