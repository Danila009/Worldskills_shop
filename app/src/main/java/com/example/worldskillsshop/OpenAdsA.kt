package com.example.worldskillsshop

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.core.net.toUri
import com.example.worldskillsshop.ViewPager.ViewPagerAdapte
import com.example.worldskillsshop.databinding.ActivityOpenAdsBinding
import com.example.worldskillsshop.db.AdsDBManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenAdsA : AppCompatActivity() {

    lateinit var binding: ActivityOpenAdsBinding
    var actBar: ActionBar? = null

    var id = 0

    var heart:MenuItem? = null

    var bul = false

    val myDbManager = AdsDBManager(this)

    var priceOpen = ""
    var titleAnnouncementOpen = ""
    var productPhotoOpen = ""
    var descriptionOpen = ""
    var Time = ""
    var Viewing = ""

    var Image_1 = ""
    var Image_2 = ""
    var Image_3 = ""
    var Image_4 = ""

    private lateinit var adapter:ViewPagerAdapte

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

        priceOpen = intent.getStringExtra(MyIntent.I_price).toString()
        titleAnnouncementOpen = intent.getStringExtra(MyIntent.I_titleAnnouncement).toString()
        productPhotoOpen = intent.getStringExtra(MyIntent.I_productPhoto).toString()
        descriptionOpen = intent.getStringExtra(MyIntent.I_Description).toString()
        Time = intent.getStringExtra(MyIntent.I_Time).toString()
        id = intent.getStringExtra(MyIntent.I_Id).toString().toInt()
        Viewing = intent.getStringExtra(MyIntent.I_Viewing).toString()

        Image_1 = intent.getStringExtra(MyIntent.AddImage_1).toString()
        Image_2 = intent.getStringExtra(MyIntent.AddImage_2).toString()
        Image_3 = intent.getStringExtra(MyIntent.AddImage_3).toString()
        Image_4 = intent.getStringExtra(MyIntent.AddImage_4).toString()


        adapter = ViewPagerAdapte(this,Image_1,Image_2,Image_3,Image_4)
        binding.ViewPager.adapter = adapter

        binding.priceOpen.text = priceOpen
        binding.titleAnnouncementOpen.text = titleAnnouncementOpen
        binding.descriptionOpen.text = intent.getStringExtra(MyIntent.I_Description)
        binding.Time.text = intent.getStringExtra(MyIntent.I_Time)
    }

    override fun onStart() {
        val y = Viewing.toInt()+1
        binding.Viewing.text = y.toString()
        update(y.toString())
        super.onStart()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.open_ads_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun update(viewing:String){
        myDbManager.openDb()
        if (Image_1!="0"&&Image_2=="0"&&Image_3=="0"&&Image_4=="0"){
            ImageUpdate(viewing,Image_1,"0","0","0")
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
            ImageUpdate(viewing,"0",Image_2,"0","0")
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
            ImageUpdate(viewing,"0","0",Image_3,"0")
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3=="0"&&Image_4!="0"){
            ImageUpdate(viewing,"0","0","0",Image_4)
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
            ImageUpdate(viewing,"0","0",Image_3,Image_4)
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
            ImageUpdate(viewing,Image_1,"0",Image_3,"0")
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
            ImageUpdate(viewing,"0",Image_2,Image_3,"0")
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
            ImageUpdate(viewing,Image_1,Image_2,"0","0")
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
            ImageUpdate(viewing,Image_1,Image_2,"0",Image_4)
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
            ImageUpdate(viewing,Image_1,Image_2,Image_3,"0")
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4!="0"){
            ImageUpdate(viewing,"0",Image_2,Image_3,Image_4)
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
            ImageUpdate(viewing,Image_1,"0",Image_3,Image_4)
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3!="0"&&Image_4!="0"){
            ImageUpdate(viewing,Image_1,Image_2,Image_3,Image_4)
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3=="0"&&Image_4!="0"){
            ImageUpdate(viewing,Image_1,"0","0",Image_4)
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
            ImageUpdate(viewing,"0",Image_2,"0",Image_4)
        }
    }

    fun ImageUpdate(viewing: String,I1:String,I2:String,I3:String,I4:String){
        CoroutineScope(Dispatchers.Main).launch{
            myDbManager.updateItem(titleAnnouncementOpen,priceOpen,descriptionOpen,intent.getStringExtra(MyIntent.I_Phone).toString(),
                I1,I2,I3,
                I4,Time,id,viewing)
        }
    }

    @SuppressLint("ResourceType")
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        heart = menu.findItem(R.id.heart)
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.heart -> {
                bul = when(bul){
                    false-> {
                        heart?.setIcon(R.drawable.heart_act)
                        true
                    }
                    true-> {
                        heart?.setIcon(R.drawable.heart)
                        false
                    }
                }
            }
            R.id.call->{

                val intentP = Intent(ACTION_DIAL)
                intentP.data = Uri.parse("tel:${intent.getStringExtra(MyIntent.I_Phone).toString()}");
                startActivity(intentP)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}