package com.example.worldskillsshop.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskillsshop.adapter_RV.adapter_cards
import com.example.worldskillsshop.data_history_RV.bank_cards
import com.example.worldskillsshop.*
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.db.AdsDBManager
import java.io.IOException
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    var thiscontext: Context? = null

    val imageReguestCode = 10

    var title = ""
    var price = 0

    var rv = 0
    var image = ""

    var Id = ArrayList<String>()
    var Phone = ArrayList<String>()
    var PriceADS = ArrayList<String>()
    var Description = ArrayList<String>()
    var COLUMN_titleADS = ArrayList<String>()
    var AddImage = ArrayList<String>()
    var AddImage_1 = ArrayList<String>()
    var AddImage_2 = ArrayList<String>()
    var AddImage_3 = ArrayList<String>()

    private val binding get() = _binding!!

    var adapter:adapter_cards? = null
    var myDbManager:AdsDBManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Главная"
        (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        thiscontext = container?.context
        adapter = adapter_cards(thiscontext!!)
        myDbManager = AdsDBManager(thiscontext!!)

        binding.floatingActionButton.setOnClickListener {
            com.example.worldskillsshop.Intent().open(thiscontext!!)
        }
        return (root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DB()
    }

    fun DB(){

        myDbManager?.openDb()
        Id = myDbManager!!.readDbDate("Id")
        Phone = myDbManager!!.readDbDate("Phone")
        PriceADS = myDbManager!!.readDbDate("PriceADS")
        Description = myDbManager!!.readDbDate("Description")
        COLUMN_titleADS = myDbManager!!.readDbDate("COLUMN_titleADS")
        AddImage = myDbManager!!.readDbDate("AddImage")
        AddImage_1 = myDbManager!!.readDbDate("AddImage_1")
        AddImage_2 = myDbManager!!.readDbDate("AddImage_2")
        AddImage_3 = myDbManager!!.readDbDate("AddImage_3")

        myDbManager?.insertToDb("0","0","0","0","0","0","0","0")


        var i = 1000
        val ran = Id.size

        while (i != 0){

            val ranD = (0..ran-1).random()
            i--

            binding.RVHOME.layoutManager = GridLayoutManager(thiscontext, 2)
            binding.RVHOME.adapter = adapter

            if(AddImage[ranD] != "0"&& AddImage_1[ranD] == "0"&& AddImage_2[ranD] == "0"&&AddImage_3[ranD] =="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_1[ranD] != "0"&&AddImage[ranD] =="0"&&AddImage_2[ranD]=="0"&&AddImage_3[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            } else if (AddImage_2[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_3[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_2[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            } else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_3[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_2[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]=="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]=="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] == "0"&&AddImage[ranD]=="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage_1[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }else if (AddImage_3[ranD] != "0"&&AddImage[ranD]!="0"&&AddImage_1[ranD]!="0"&&AddImage_2[ranD]!="0"){
                val card = bank_cards(PriceADS[ranD],AddImage[ranD],COLUMN_titleADS[ranD], Description[ranD])
                adapter?.addCard(card)
            }
        }
    }

    override fun onDestroy() {
        val myDbManager = AdsDBManager(thiscontext!!)
        super.onDestroy()
        myDbManager.closeDb()
    }
}