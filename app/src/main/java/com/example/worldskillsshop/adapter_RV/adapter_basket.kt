package com.example.worldskillsshop.adapter_RV

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.R
import com.example.worldskillsshop.data_history_RV.basket
import com.example.worldskillsshop.databinding.BasketRvBinding
import com.example.worldskillsshop.db.BasketDBManager

class adapter_basket: RecyclerView.Adapter<adapter_basket.CardHolder>() {

    val CardList = ArrayList<basket>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item) {

        var binding = BasketRvBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(card: basket) = with(binding){

            productPhotoBasket.setImageURI(card.productPhoto.toUri())
            titleAnnouncementBasket.text = card.titleAnnouncement
            description.text = card.description
            priceBasket.text = card.price + " руб."
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_rv, parent, false)

        return CardHolder(view)
    }


    override fun getItemCount(): Int {

        return CardList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCard(card:basket){

        CardList.add(card)
        notifyDataSetChanged()
    }

    fun removeItem(pos:Int, list:ArrayList<String>,db:BasketDBManager){

        db.removeDb(list[pos])
        CardList.removeAt(pos)
        notifyItemRangeChanged(0,CardList.size)
        notifyItemRemoved(pos)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(CardList[position])
    }
}