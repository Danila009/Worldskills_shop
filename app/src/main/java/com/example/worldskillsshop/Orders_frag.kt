package com.example.worldskillsshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.databinding.FragmentOrdersFragBinding

class Orders_frag : Fragment() {

    private var _binding: FragmentOrdersFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = "Заказы"
        setHasOptionsMenu(true)

        _binding = FragmentOrdersFragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Glide
            .with(this)
            .load("https://im0-tub-ru.yandex.net/i?id=a931db9b0a2d0c38cf1caae42432e4c2-l&n=13")
            .into(binding.imageView3)

        return root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val id = item.itemId
        when (id)
        {
            android.R.id.home -> view?.findNavController()?.navigate(R.id.navigation_notifications)
        }
        return super.onOptionsItemSelected(item)
    }
}