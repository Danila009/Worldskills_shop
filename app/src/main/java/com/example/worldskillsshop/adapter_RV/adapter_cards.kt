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
import com.example.worldskillsshop.db.BasketDBManager
import com.example.worldskillsshop.db.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            when {
                card.AddImage_1 != "0" -> {
                    productPhoto.setImageURI(card.AddImage_1.toUri())
                }
                card.AddImage_2 != "0" -> {
                    productPhoto.setImageURI(card.AddImage_2.toUri())
                }
                card.AddImage_3 != "0" -> {
                    productPhoto.setImageURI(card.AddImage_3.toUri())
                }
                card.AddImage_4 != "0" -> {
                    productPhoto.setImageURI(card.AddImage_4.toUri())
                }
            }

            titleAnnouncement.text = card.titleAnnouncement

            productPhoto.setOnClickListener {


                val Intent = Intent(context, OpenAdsA::class.java).apply {

                    putExtra(MyIntent.I_price, card.price)
                    putExtra(MyIntent.I_productPhoto, card.productPhoto)
                    putExtra(MyIntent.I_titleAnnouncement, card.titleAnnouncement)
                    putExtra(MyIntent.I_Description, card.description)
                    putExtra(MyIntent.I_Time, card.time)
                    putExtra(MyIntent.I_Phone, card.phone)
                    putExtra(MyIntent.I_Id, card.id)
                    putExtra(MyIntent.I_Viewing, card.Viewing)

                    putExtra(MyIntent.AddImage_1, card.AddImage_1)
                    putExtra(MyIntent.AddImage_2, card.AddImage_2)
                    putExtra(MyIntent.AddImage_3, card.AddImage_3)
                    putExtra(MyIntent.AddImage_4, card.AddImage_4)

                }
                context.startActivity(Intent)
            }

            basketBut.setOnClickListener {
                val Db = BasketDBManager(context)

                Db.openDb()
                CoroutineScope(Dispatchers.Main).launch {
                    Db.insertDb(card.titleAnnouncement,card.price,card.description,
                        card.phone,card.AddImage_1,
                        card.AddImage_2,card.AddImage_3,card.AddImage_4,card.time,card.Viewing)
                }
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

        CardList.add(card)
        notifyDataSetChanged()
    }
}