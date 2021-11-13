package com.example.worldskillsshop.adapter_RV

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.R
import com.example.worldskillsshop.data_history_RV.Message
import com.example.worldskillsshop.data_history_RV.bank_cards
import com.example.worldskillsshop.data_history_RV.progressRV
import com.example.worldskillsshop.databinding.AnnouncementRvBinding
import com.example.worldskillsshop.databinding.ProgressRvBinding

class adapter_progress(): RecyclerView.Adapter<adapter_progress.CardHolder>() {

    val CardList = ArrayList<progressRV>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item){
        var binding = ProgressRvBinding.bind(item)

        fun bind(card: progressRV) = with(binding){
            textProgress.text = card.text_progress

            when(card.medal){
                "1"->medal.setImageResource(R.drawable.bronzemedalpro)
                "2"->medal.setImageResource(R.drawable.silvermedalpro)
                "3"->medal.setImageResource(R.drawable.goldmedalpro)
                "4"->medal.setImageResource(R.drawable.platinum)
                "5"->medal.setImageResource(R.drawable.medal)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.progress_rv, parent, false)

        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(CardList[position])
    }

    override fun getItemCount(): Int {
        return CardList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCard(card: progressRV){

        CardList.add(card)
        notifyDataSetChanged()
    }
}