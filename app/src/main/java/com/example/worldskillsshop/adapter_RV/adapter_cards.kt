package com.example.worldskillsshop.adapter_RV

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.data_history_RV.bank_cards
import com.example.worldskillsshop.*
import com.example.worldskillsshop.databinding.AnnouncementRvBinding

class adapter_cards(contextM: Context) : RecyclerView.Adapter<adapter_cards.CardHolder>() {

    val CardList = ArrayList<bank_cards>()
    val context = contextM

    class CardHolder(item: View, contextV: Context): RecyclerView.ViewHolder(item) {

        var binding = AnnouncementRvBinding.bind(item)
        val context = contextV
        var build = false

        @SuppressLint("SetTextI18n")
        fun bind(card: bank_cards) = with(binding){

            price.text = "${card.price} Р"
            productPhoto.setImageURI(card.productPhoto.toUri())
            titleAnnouncement.text = card.titleAnnouncement

            basketBut.setOnClickListener {
                Log.d("sfsDSASDVd","ajdabhvapdvuhibpshviYAFDPBI")


                val Intent = Intent(context, OpenAdsA::class.java).apply {

                    putExtra(MyIntent.I_price, card.price)
                    putExtra(MyIntent.I_productPhoto, card.productPhoto)
                    putExtra(MyIntent.I_titleAnnouncement, card.titleAnnouncement)
                }
                context.startActivity(Intent)
            }

            heart.setOnClickListener {

                when(build)
                {
                    false-> {
                        build = true
                        heart.setImageResource(R.drawable.heart_act)
                    }

                    true->{
                        build = false
                        heart.setImageResource(R.drawable.heart)
                    }
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_rv, parent, false)

        return CardHolder(view, context)
    }



    override fun onBindViewHolder(holder: CardHolder, position: Int) {

        holder.bind(CardList[position])
    }



    override fun getItemCount(): Int {

        return CardList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCard(card: bank_cards){

        //CardList.clear()
        CardList.add(card)
        notifyDataSetChanged()
    }
}