package com.example.worldskillsshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import com.example.worldskillsshop.databinding.ActivityAddingAdsBinding
import java.io.IOException

class addingAdsA : AppCompatActivity() {

    lateinit var binding: ActivityAddingAdsBinding

    var int_image = 0

    val imageReguestCode = 10

    var id = ""

    var AddImage = ""
    var AddImage_1 = ""
    var AddImage_2 = ""
    var AddImage_3 = ""

    var AddImageProver = false
    var AddImageProver_1 = false
    var AddImageProver_2 = false
    var AddImageProver_3 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_ads)

        binding = ActivityAddingAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.butAddImage.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 1
            AddImageProver = true
        }

        binding.butAddImage2.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 2
            AddImageProver_1 = true
        }

        binding.butAddImage3.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 3
            AddImageProver_2 = true
        }

        binding.butAddImage4.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 4
            AddImageProver_3 = true
        }

        binding.proceed.setOnClickListener {

            if (binding.titleADS.text.isNotEmpty()
                && binding.PriceADS.text.isNotEmpty()
                && binding.Phone.text.isNotEmpty()
                && binding.Description.text.isNotEmpty()
                && int_image != 0){

                val intent = Intent(this, MainActivity::class.java).apply {

                    putExtra(MyIntent.I_titleADS, binding.titleADS.text)
                    putExtra(MyIntent.I_PriceADS, binding.PriceADS.text)
                    putExtra(MyIntent.I_Phone, binding.Phone.text)
                    putExtra(MyIntent.I_Description, binding.Description.text)

                    if (AddImageProver){
                        putExtra(MyIntent.AddImage_1, AddImage)
                    } else if (AddImageProver_1){
                        putExtra(MyIntent.AddImage_2, AddImage_1)
                    } else if (AddImageProver_2){
                        putExtra(MyIntent.AddImage_3, AddImage_2)
                    } else if (AddImageProver_3){
                        putExtra(MyIntent.AddImage_4, AddImage_3)
                    }else if (AddImageProver && AddImageProver_1){
                        putExtra(MyIntent.AddImage_1, AddImage)
                        putExtra(MyIntent.AddImage_2, AddImage_1)
                    } else if (AddImageProver_2 && AddImageProver_3){
                        putExtra(MyIntent.AddImage_3, AddImage_2)
                        putExtra(MyIntent.AddImage_4, AddImage_3)
                    }else if (AddImageProver && AddImageProver_2){
                        putExtra(MyIntent.AddImage_1, AddImage)
                        putExtra(MyIntent.AddImage_3, AddImage_2)
                    }else if (AddImageProver_1 && AddImageProver_3){
                        putExtra(MyIntent.AddImage_2, AddImage_1)
                        putExtra(MyIntent.AddImage_4, AddImage_3)
                    }else if (AddImageProver && AddImageProver_3 ){
                        putExtra(MyIntent.AddImage_1, AddImage)
                        putExtra(MyIntent.AddImage_4, AddImage_3)
                    }else if (AddImageProver_1 && AddImageProver_2){
                        putExtra(MyIntent.AddImage_2, AddImage_1)
                        putExtra(MyIntent.AddImage_3, AddImage_2)
                    }else{
                        putExtra(MyIntent.AddImage_1, AddImage)
                        putExtra(MyIntent.AddImage_2, AddImage_1)
                        putExtra(MyIntent.AddImage_3, AddImage_2)
                        putExtra(MyIntent.AddImage_4, AddImage_3)
                    }
                }
                startActivity(intent)
            }
        }
    }

    fun addingAds(id:String){

        when(int_image){

            1-> binding.butAddImage.setImageURI(id.toUri())
            2-> binding.butAddImage2.setImageURI(id.toUri())
            3-> binding.butAddImage3.setImageURI(id.toUri())
            4-> binding.butAddImage4.setImageURI(id.toUri())
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageReguestCode) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                if (data != null) {
                    try {
                        addingAds(data.data.toString())
                        id = data.data.toString()

                        when(int_image){
                            1 -> AddImage = data.data.toString()
                            2 -> AddImage_1 = data.data.toString()
                            3 -> AddImage_2= data.data.toString()
                            4 -> AddImage_3 = data.data.toString()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}