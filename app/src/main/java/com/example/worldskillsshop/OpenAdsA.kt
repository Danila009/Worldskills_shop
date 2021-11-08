package com.example.worldskillsshop

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.core.net.toUri
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
        actBar?.setDisplayHomeAsUpEnabled(true)
        actBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))
        actBar?.title = ""

        val intent= intent

        binding.priceOpen.text = intent.getStringExtra(MyIntent.I_price)
        binding.titleAnnouncementOpen.text = intent.getStringExtra(MyIntent.I_titleAnnouncement)
        binding.productPhotoOpen.setImageURI(intent.getStringExtra(MyIntent.I_productPhoto)?.toUri())
        binding.descriptionOpen.text = intent.getStringExtra(MyIntent.I_Description)
        binding.Time.text = intent.getStringExtra(MyIntent.I_Time)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.open_ads_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.heart -> {
                heart()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun heart() {
        when(bul){
            false-> {
                bul = true
            }
            true-> {
                bul = false
            }
        }
    }
}