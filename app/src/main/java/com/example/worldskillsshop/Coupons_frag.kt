package com.example.worldskillsshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsshop.adapter_RV.adapter_coupon
import com.example.worldskillsshop.adapter_RV.adapter_message
import com.example.worldskillsshop.data_history_RV.Message
import com.example.worldskillsshop.data_history_RV.сoupon
import com.example.worldskillsshop.databinding.FragmentCouponsFragBinding
import com.example.worldskillsshop.databinding.FragmentPostsFragBinding

class Coupons_frag : Fragment() {

    private var _binding: FragmentCouponsFragBinding? = null
    private val binding get() = _binding!!

    private val adapter = adapter_coupon()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCouponsFragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = "Купоны"
        setHasOptionsMenu(true)

        rvFun("500 руб.","2000 руб.","12.11.2021 12:00:00","Первая покупка")

        return root
    }

    fun rvFun(DiscountAmount:String,From:String,remainder:String,nameCoupon:String){

        binding.RVCOUPONS.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.RVCOUPONS.adapter = adapter

        val card = сoupon(DiscountAmount,From,remainder,nameCoupon)
        adapter.addCard(card)

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