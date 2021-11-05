package com.example.worldskillsshop.adapter_RV

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.R
import com.example.worldskillsshop.data_history_RV.Message
import com.example.worldskillsshop.databinding.MessageRvBinding

class adapter_message: RecyclerView.Adapter<adapter_message.CardHolder>() {

    val CardList = ArrayList<Message>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item){

        var binding = MessageRvBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(card: Message) = with(binding){

            if (card.Icon_message != "1"){
                IconMessage.setImageURI(card.Icon_message.toUri())
            }
            messageRV.text = card.message
            nick.text = card.Nick
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_message.CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_rv, parent, false)

        return adapter_message.CardHolder(view)
    }

    override fun onBindViewHolder(holder: adapter_message.CardHolder, position: Int) {

        holder.bind(CardList[position])
    }

    override fun getItemCount(): Int {
        return CardList.size
    }

    fun addCard(card:Message){

        CardList.add(card)
        notifyDataSetChanged()
    }
}