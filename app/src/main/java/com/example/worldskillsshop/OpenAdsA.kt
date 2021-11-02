package com.example.worldskillsshop

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.core.net.toUri
import com.example.worldskillbank.adapter_RV.adapter_cards
import com.example.worldskillsshop.databinding.ActivityOpenAdsBinding

class OpenAdsA : AppCompatActivity() {

    lateinit var binding: ActivityOpenAdsBinding
    var actBar: ActionBar? = null

    var bul = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_ads)

        binding = ActivityOpenAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actBar = supportActionBar
        actBar?.hide()

        val intent= intent

        binding.priceOpen.text = intent.getStringExtra(MyIntent.I_price)
        binding.titleAnnouncementOpen.text = intent.getStringExtra(MyIntent.I_titleAnnouncement)
        binding.productPhotoOpen.setImageURI(intent.getStringExtra(MyIntent.I_productPhoto)?.toUri())

        binding.heartBut.setOnClickListener {

            when(bul){

                false-> {
                    bul = true
                    binding.heartBut.setImageResource(R.drawable.heart_act)
                }
                true-> {
                    bul = false
                    binding.heartBut.setImageResource(R.drawable.heart)
                }
            }
        }

    }
}