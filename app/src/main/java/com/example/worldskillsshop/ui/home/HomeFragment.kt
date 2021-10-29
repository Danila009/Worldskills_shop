package com.example.worldskillsshop.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldskillbank.adapter_RV.adapter_cards
import com.example.worldskillbank.data_history_RV.bank_cards
import com.example.worldskillsshop.R
import com.example.worldskillsshop.databinding.AnnouncementRvBinding
import com.example.worldskillsshop.databinding.DialogAddingAnnouncedBinding
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.db.MuDbManager
import java.io.IOException

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    var thiscontext: Context? = null

    val imageReguestCode = 10

    var title = ""
    var price = 0

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        thiscontext = container?.context

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Главная"
        (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#141414")))

        binding.floatingActionButton.setOnClickListener {


            val dialogBuilder = android.app.AlertDialog.Builder(this.context)
            val bindingChange = DialogAddingAnnouncedBinding.inflate(layoutInflater)
            dialogBuilder.setView(bindingChange.root)

            val dialog: android.app.AlertDialog = dialogBuilder.show()

            val proceed_but_dialog = dialog.findViewById<Button>(R.id.proceed_but_dialog)
            val title_dialog = dialog.findViewById<TextView>(R.id.title_dialog)
            val price_dialog = dialog.findViewById<TextView>(R.id.price_dialog)
            val Cancel_but_dialog = dialog.findViewById<Button>(R.id.Cancel_but_dialog)


            proceed_but_dialog.setOnClickListener {

                dialog.dismiss()

                if (title_dialog.text.toString().isNotEmpty() && price_dialog.text.toString().isNotEmpty())
                {
                    title = title_dialog.text.toString()
                    price = price_dialog.text.toString().toInt()

                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        imageReguestCode)
                }
            }

            Cancel_but_dialog.setOnClickListener {

                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawableResource(R.drawable.krujok)

        }
        return (root)
    }


    fun addingAds(id:String){

        val adapter = adapter_cards(thiscontext!!)
        val myDbManager = MuDbManager(thiscontext!!)
        binding.RV.layoutManager = GridLayoutManager(thiscontext!!, 2)
        binding.RV.adapter = adapter

        //myDbManager.openDb()
        //myDbManager.insertToDb(id, price.toString(),title)

        val card = bank_cards(price,id,title)
        adapter.addCard(card)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageReguestCode) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                if (data != null) {
                    try {
                        addingAds(data.data.toString())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //val myDbManager = MuDbManager(thiscontext!!)
        //myDbManager.closeDb()
    }
}