package com.example.worldskillsshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldskillsshop.adapter_RV.adapter_coupon
import com.example.worldskillsshop.adapter_RV.adapter_message
import com.example.worldskillsshop.data_history_RV.Message
import com.example.worldskillsshop.data_history_RV.сoupon
import com.example.worldskillsshop.databinding.FragmentCouponsFragBinding
import com.example.worldskillsshop.databinding.FragmentPostsFragBinding

class Coupons_frag : Fragment() {

    private var _binding: FragmentCouponsFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCouponsFragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvFun("500 руб.","2000 руб.","12.11.2021 12:00:00","Первая покупка")

        return root
    }

    fun rvFun(DiscountAmount:String,From:String,remainder:String,nameCoupon:String){
        val adapter = adapter_coupon()

        binding.RV.layoutManager = GridLayoutManager(this.context, 1)
        binding.RV.adapter = adapter

        val card = сoupon(DiscountAmount,From,remainder,nameCoupon)
        adapter.addCard(card)

    }
}