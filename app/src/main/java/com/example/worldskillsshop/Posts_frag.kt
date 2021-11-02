package com.example.worldskillsshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldskillbank.adapter_RV.adapter_cards
import com.example.worldskillbank.data_history_RV.bank_cards
import com.example.worldskillsshop.adapter_RV.adapter_message
import com.example.worldskillsshop.data_history_RV.Message
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.databinding.FragmentPostsFragBinding

class Posts_frag : Fragment() {

    private var _binding: FragmentPostsFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsFragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvFun("Сообщение","Bot","1")

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = "Сообщение"
        setHasOptionsMenu(true)

        return root
    }

    fun rvFun(message:String, Nick:String,Icon_message: String){
        val adapter = adapter_message()

        binding.RV.layoutManager = GridLayoutManager(this.context, 1)
        binding.RV.adapter = adapter

        val card = Message(Icon_message,Nick,message)
        adapter.addCard(card)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val id = item.itemId
        when (id)
        {
            android.R.id.home -> view?.findNavController()?.navigate(R.id.navigation_notifications)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        fun newInstance() = Posts_frag()
    }
}