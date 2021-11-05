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

        DB()

        binding.floatingActionButton.setOnClickListener {
            com.example.worldskillsshop.Intent().open(thiscontext!!)
        }
        return (root)
    }

    fun DB(){
        val myDbManager = AdsDBManager(thiscontext!!)
        myDbManager.openDb()
        Id = myDbManager.readDbDate("Id")
        Phone = myDbManager.readDbDate("Phone")
        PriceADS = myDbManager.readDbDate("PriceADS")
        Description = myDbManager.readDbDate("Description")
        COLUMN_titleADS = myDbManager.readDbDate("COLUMN_titleADS")
        AddImage = myDbManager.readDbDate("AddImage")
        AddImage_1 = myDbManager.readDbDate("AddImage_1")
        AddImage_2 = myDbManager.readDbDate("AddImage_2")
        AddImage_3 = myDbManager.readDbDate("AddImage_3")
        var i = Id.size

        while (i > 0){

            rvP(i)
            i--
        }
    }

    fun rvP(i:Int){
        val adapter = adapter_cards(thiscontext!!)
        binding.RVHOME.layoutManager = GridLayoutManager(thiscontext!!, 2)
        binding.RVHOME.adapter = adapter

        val card = bank_cards(PriceADS[i],"content://com.android.providers.media.documents/document/image%3A25",COLUMN_titleADS[i])
        adapter.addCard(card)
    }


    override fun onDestroy() {
        val myDbManager = AdsDBManager(thiscontext!!)
        super.onDestroy()
        myDbManager.closeDb()
    }
}