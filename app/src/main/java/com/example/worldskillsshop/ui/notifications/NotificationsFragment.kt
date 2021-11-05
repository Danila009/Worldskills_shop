package com.example.worldskillsshop.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.worldskillsshop.R
import com.example.worldskillsshop.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    val list = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Username"
        setHasOptionsMenu(true)

        list.add("  Объявления ")
        list.add("  Заказы")
        list.add("  Сообщения")
        list.add("  Отзывы")
        list.add("  Избранное")
        list.add("  Купоны")
        list.add("  Настройки")

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.List.adapter = ArrayAdapter(
            requireActivity().applicationContext,
            R.layout.text_color_white,
            list
        )

        binding.List.setOnItemClickListener{_, view, i, _ ->

            when(i){
                0-> view.findNavController().navigate(R.id.ads_frag)
                1-> view.findNavController().navigate(R.id.orders_frag)
                2-> view.findNavController().navigate(R.id.posts_frag)
                3-> view.findNavController().navigate(R.id.reviews_frag)
                4-> view.findNavController().navigate(R.id.favorites_frag)
                5-> view.findNavController().navigate(R.id.coupons_frag)
                6-> view.findNavController().navigate(R.id.settings_frag)
            }
            list.clear()
        }

        return root
    }


}