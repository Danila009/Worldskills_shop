package com.example.worldskillbank.adapter_RV

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillbank.data_history_RV.bank_cards
import com.example.worldskillsshop.R
import com.example.worldskillsshop.databinding.AnnouncementRvBinding

class adapter_cards: RecyclerView.Adapter<adapter_cards.CardHolder>() {

    val CardList = ArrayList<bank_cards>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item) {

        var binding = AnnouncementRvBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(card: bank_cards) = with(binding){

            price.text = "${card.price} Р"
            productPhoto.setImageURI(card.productPhoto.toUri())
            titleAnnouncement.text = card.titleAnnouncement
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_rv, parent, false)

        return CardHolder(view)
    }



    override fun onBindViewHolder(holder: CardHolder, position: Int) {

        holder.bind(CardList[position])
    }



    override fun getItemCount(): Int {

        return CardList.size
    }

    fun addCard(card:bank_cards){

        CardList.add(card)
        notifyDataSetChanged()
    }

}