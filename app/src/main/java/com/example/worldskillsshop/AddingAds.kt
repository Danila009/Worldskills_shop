package com.example.worldskillsshop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.worldskillsshop.databinding.FragmentAddingAdsBinding
import com.example.worldskillsshop.databinding.FragmentNotificationsBinding
import java.io.IOException

class AddingAds : Fragment() {

    private var _binding: FragmentAddingAdsBinding? = null
    private val binding get() = _binding!!

    var int_image = 0

    val imageReguestCode = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddingAdsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.butAddImage.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 1
        }

        binding.butAddImage2.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 2
        }

        binding.butAddImage3.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 3
        }

        binding.butAddImage4.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageReguestCode)
            int_image = 4
        }

        return root
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
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}