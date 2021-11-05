package com.example.worldskillsshop.adapter_RV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.R
import com.example.worldskillsshop.data_history_RV.сoupon
import com.example.worldskillsshop.databinding.CouponsRvBinding

class adapter_coupon: RecyclerView.Adapter<adapter_coupon.CardHolder>() {

    val CardList = ArrayList<сoupon>()
    //val context = contextM

    class CardHolder(item: View, /*contextV: Context*/): RecyclerView.ViewHolder(item){

        var binding = CouponsRvBinding.bind(item)
        //val context = contextV
        var build = false
        fun bind(card: сoupon) = with(binding){

            DiscountAmount.text = card.DiscountAmount
            from.append(card.From)
            nameCoupon.text = card.nameCoupon
            remainder.text = card.remainder
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.coupons_rv, parent, false)
        return CardHolder(view, /*context*/)

    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(CardList[position])
    }

    override fun getItemCount(): Int {
        return CardList.size
    }

    fun addCard(card:сoupon){

        CardList.add(card)
        notifyDataSetChanged()
    }
}