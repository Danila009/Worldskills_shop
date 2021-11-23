package com.example.worldskillsshop.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldskillsshop.MainActivity
import com.example.worldskillsshop.R
import com.example.worldskillsshop.adapter_RV.adapter_basket
import com.example.worldskillsshop.adapter_RV.adapter_coupon
import com.example.worldskillsshop.data_history_RV.basket
import com.example.worldskillsshop.data_history_RV.сoupon
import com.example.worldskillsshop.databinding.FragmentDashboardBinding
import com.example.worldskillsshop.db.BasketDBManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    var Db:BasketDBManager?=null
    var thiscontext: Context?=null

    private val adapter = adapter_basket()

    var ID = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Корзина"
        setHasOptionsMenu(true)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        thiscontext = container?.context

        Db = BasketDBManager(thiscontext!!)
        Db?.openDb()

        ID = dbDate("Id")

        binding.openCup.setOnClickListener {
            binding.List.height
        }

        return root
    }

    fun delete():ItemTouchHelper{

        return ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition,ID,Db!!)
                Log.d("sfssd",dbDate("Id").toString())
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var Id:Int = ID.size-1

        while (Id != 0){

            rvFun(dbDate("Description")[Id],dbDate("AddImage")[Id],dbDate("COLUMN_titleADS")[Id],dbDate("PriceADS")[Id])

            Id--
        }
    }

    fun rvFun(description:String,productPhoto:String,titleAnnouncement:String,price:String){

        binding.recyclerView3.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView3.adapter = adapter

        val swapHelper = delete()
        swapHelper.attachToRecyclerView(binding.recyclerView3)

        val card = basket(description,productPhoto,titleAnnouncement,price)
        adapter.addCard(card)

    }

    fun dbDate(id:String):ArrayList<String>{
        val dataList = Db!!.readDbDate(id)
       return dataList
    }

    override fun onDestroy() {
        super.onDestroy()
        Db?.closeDb()
    }
}