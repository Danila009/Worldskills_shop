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
import androidx.core.widget.doOnTextChanged
import com.example.worldskillsshop.databinding.ActivityAddingAdsBinding
import com.example.worldskillsshop.db.AdsDBManager
import com.example.worldskillsshop.ui.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        val category = resources.getStringArray(R.array.categoryCreation)
        val arrayAdapterCategory = ArrayAdapter(this, R.layout.category_item, category)
        binding.category.setAdapter(arrayAdapterCategory)

        val sellers = resources.getStringArray(R.array.SellersCreation)
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

            if (binding.InputEditTextTitle.text.toString().isNotEmpty()
                && binding.InputEditTextPrice.text.toString().isNotEmpty()
                && binding.InputEditTextPhone.text.toString().isNotEmpty()
                && binding.InputEditTextDescription.text.toString().isNotEmpty()
                && int_image != 0
                && binding.InputEditTextPhone.text.toString().count() == 10
                && binding.InputEditTextTitle.text.toString().count() <= 50
                && binding.InputEditTextDescription.text.toString().count() <= 300
                &&binding.InputEditTextPrice.text.toString().toInt() >= 100
            ) {
                if (binding.InputEditTextPrice.text.toString().toInt() <= 100000000){
                    val intent = Intent(this, MainActivity::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        if (AddImageProver && !AddImageProver_1 && !AddImageProver_2 && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                AddImage,
                                "0",
                                "0",
                                "0",
                                getCurrentTime(),
                                "0")
                        } else if (AddImageProver_1 && !AddImageProver && !AddImageProver_2 && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                AddImage_1,
                                "0",
                                "0",
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver_2 && !AddImageProver && !AddImageProver_1 && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                "0",
                                AddImage_2,
                                "0",
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver_3 && !AddImageProver && !AddImageProver_2 && !AddImageProver_1) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                "0",
                                "0",
                                AddImage_3,
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver && AddImageProver_1 && !AddImageProver_2 && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                AddImage,
                                AddImage_1,
                                "0",
                                "0",
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver_2 && AddImageProver_3 && !AddImageProver && !AddImageProver_1) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                "0",
                                AddImage_2,
                                AddImage_3,
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver && AddImageProver_2 && !AddImageProver_1 && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                AddImage,
                                "0",
                                AddImage_2,
                                "0",
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver_1 && AddImageProver_3 && !AddImageProver && !AddImageProver_2) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                AddImage_1,
                                "0",
                                AddImage_3,
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver && AddImageProver_3 && !AddImageProver_1 && !AddImageProver_2) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                AddImage,
                                "0",
                                "0",
                                AddImage_3,
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver_1 && AddImageProver_2 && !AddImageProver && !AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
                                "0",
                                AddImage_1,
                                AddImage_2,
                                "0",
                                getCurrentTime(),
                                "0"
                            )
                        } else if (AddImageProver && AddImageProver_2 && AddImageProver_1 && AddImageProver_3) {
                            myDbManager.insertToDb(
                                binding.InputEditTextTitle.text.toString(),
                                binding.InputEditTextPrice.text.toString(),
                                binding.InputEditTextDescription.text.toString(),
                                binding.InputEditTextPhone.text!!.toString(),
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
                } else if (binding.InputEditTextPhone.text.toString().count() != 9 || binding.InputEditTextPhone.text!!.isEmpty()) {
                    Log.d("dsfsfssfdsd",binding.InputEditTextPhone.text.toString())
                    binding.InputLayoutPhone.error = "Номер телефона ведён не коректно"
                } else if (binding.InputEditTextDescription.text.toString().count() > 300 || binding.InputEditTextDescription.text.toString().isEmpty()) {
                    binding.InputLayoutPhone.error = null
                    bar?.title = "От 1 до 300 символов в описание"
                } else if (binding.InputEditTextTitle.text.toString().count() > 50 || binding.InputEditTextTitle.text.toString().isEmpty()) {
                    binding.InputLayoutPhone.error = null
                    bar?.title = "От 1 до 50 символов в название"
                } else if (binding.InputEditTextPrice.text.toString().toInt() > 100000000 || binding.InputEditTextPrice.text.toString().isEmpty() || binding.InputEditTextPrice.text.toString().count().toString().toInt() < 100) {
                    binding.InputLayoutPhone.error = null
                    bar?.title = "От 100 до 100000000 руб."
                } else if (int_image == 0) {
                    bar?.title = "Загрузите фото"
                }
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