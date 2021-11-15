package com.example.worldskillsshop

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import com.example.worldskillsshop.databinding.ActivityAddingAdsBinding
import com.example.worldskillsshop.db.AdsDBManager
import com.example.worldskillsshop.ui.home.HomeFragment
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

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

    val myDbManager = AdsDBManager(this)

    var bar:ActionBar? = null

    override fun onResume() {
        super.onResume()
        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(this, R.layout.category_item, category)
        binding.category.setAdapter(arrayAdapterCategory)

        val sellers = resources.getStringArray(R.array.Sellers)
        val arrayAdapterSellers = ArrayAdapter(this, R.layout.category_item, sellers)
        binding.sellers.setAdapter(arrayAdapterSellers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_ads)

        binding = ActivityAddingAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))
        bar?.title = "Добавить объявление"


        myDbManager.openDb()

        binding.butAddImage.setOnClickListener {

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imageReguestCode)
            int_image = 1
            AddImageProver = true
        }

        binding.butAddImage2.setOnClickListener {

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imageReguestCode)
            int_image = 2
            AddImageProver_1 = true
        }

        binding.butAddImage3.setOnClickListener {

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imageReguestCode)
            int_image = 3
            AddImageProver_2 = true
        }

        binding.butAddImage4.setOnClickListener {

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, imageReguestCode)
            int_image = 4
            AddImageProver_3 = true
        }

        binding.proceed.setOnClickListener {

            if (binding.titleADS.text.isNotEmpty()
                && binding.PriceADS.text.isNotEmpty()
                && binding.Phone.text.isNotEmpty()
                && binding.Description.text.isNotEmpty()
                && int_image != 0
                && binding.Phone.text.count() == 11
                && binding.titleADS.text.count() <= 50
                && binding.Description.text.count() <= 300
                &&binding.PriceADS.text.toString().toInt() >= 100
            ) {
                if (binding.PriceADS.text.toString().toInt() <= 100000000){
                    val intent = Intent(this, MainActivity::class.java)

                    if (AddImageProver && !AddImageProver_1 && !AddImageProver_2 && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            AddImage,
                            "0",
                            "0",
                            "0",
                            getCurrentTime(),
                            "0")
                    } else if (AddImageProver_1 && !AddImageProver && !AddImageProver_2 && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            AddImage_1,
                            "0",
                            "0",
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver_2 && !AddImageProver && !AddImageProver_1 && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            "0",
                            AddImage_2,
                            "0",
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver_3 && !AddImageProver && !AddImageProver_2 && !AddImageProver_1) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            "0",
                            "0",
                            AddImage_3,
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver && AddImageProver_1 && !AddImageProver_2 && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            AddImage,
                            AddImage_1,
                            "0",
                            "0",
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver_2 && AddImageProver_3 && !AddImageProver && !AddImageProver_1) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(), binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            "0",
                            AddImage_2,
                            AddImage_3,
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver && AddImageProver_2 && !AddImageProver_1 && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            AddImage,
                            "0",
                            AddImage_2,
                            "0",
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver_1 && AddImageProver_3 && !AddImageProver && !AddImageProver_2) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            AddImage_1,
                            "0",
                            AddImage_3,
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver && AddImageProver_3 && !AddImageProver_1 && !AddImageProver_2) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            AddImage,
                            "0",
                            "0",
                            AddImage_3,
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver_1 && AddImageProver_2 && !AddImageProver && !AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            "0",
                            AddImage_1,
                            AddImage_2,
                            "0",
                            getCurrentTime(),
                            "0"
                        )
                    } else if (AddImageProver && AddImageProver_2 && AddImageProver_1 && AddImageProver_3) {
                        myDbManager.insertToDb(
                            binding.titleADS.text.toString(),
                            binding.PriceADS.text.toString(),
                            binding.Description.text.toString(),
                            binding.Phone.text.toString(),
                            AddImage,
                            AddImage_1,
                            AddImage_2,
                            AddImage_3,
                            getCurrentTime(),
                            "0"
                        )
                    }
                    startActivity(intent)
                }
            } else if (binding.Phone.text.count() != 11 || binding.Phone.text.isEmpty()) {
                binding.Phone.setTextColor(Color.RED)
                bar?.title = "Номер введен некорректно"
            } else if (binding.Description.text.count() > 300 || binding.Description.text.isEmpty()) {
                binding.Description.setTextColor(Color.RED)
                binding.Phone.setTextColor(Color.GREEN)
                bar?.title = "От 1 до 300 символов в описание"
            } else if (binding.titleADS.text.count() > 50 || binding.titleADS.text.isEmpty()) {
                binding.Description.setTextColor(Color.GREEN)
                binding.titleADS.setTextColor(Color.RED)
                binding.Phone.setTextColor(Color.GREEN)
                bar?.title = "От 1 до 50 символов в название"
            } else if (binding.PriceADS.text.toString().toInt() > 100000000 || binding.PriceADS.text.isEmpty() || binding.PriceADS.text.count().toString().toInt() < 100) {
                binding.PriceADS.setTextColor(Color.RED)
                binding.Description.setTextColor(Color.GREEN)
                binding.titleADS.setTextColor(Color.GREEN)
                binding.Phone.setTextColor(Color.GREEN)
                bar?.title = "От 100 до 100000000 руб."
            } else if (int_image == 0) {
                binding.PriceADS.setTextColor(Color.GREEN)
                binding.Description.setTextColor(Color.GREEN)
                binding.titleADS.setTextColor(Color.GREEN)
                binding.Phone.setTextColor(Color.GREEN)
                bar?.title = "Загрузите фото"
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

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }

    private fun getCurrentTime():String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yy hh:mm", Locale.getDefault())
        val fTime = formatter.format(time)
        return fTime
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageReguestCode&&resultCode == RESULT_OK) {
            addingAds(data?.data.toString())
            id = data?.data.toString()
            when(int_image) {
                1 -> AddImage = data?.data.toString()
                2 -> AddImage_1 = data?.data.toString()
                3 -> AddImage_2 = data?.data.toString()
                4 -> AddImage_3 = data?.data.toString()
            }
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }
}