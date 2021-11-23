package com.example.worldskillsshop.ui.notifications

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.worldskillsshop.R
import com.example.worldskillsshop.addingAdsA
import com.example.worldskillsshop.databinding.FragmentNotificationsBinding
import com.example.worldskillsshop.db.UserDBManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    var dbUser: UserDBManager? = null
    var thiscontext: Context? = null

    val list = ArrayList<String>()

    var IdUserDB:ArrayList<String>? = null
    var NameUserDB:ArrayList<String>? = null

    @SuppressLint("SetTextI18n")
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
        thiscontext = container?.context

        binding.username.text = DbDate("NAME")
        binding.priseuser.text = DbDate("PRICE") + " руб."
        binding.profileImage.setImageURI(DbDate("IMAGE").toUri())

        binding.List.adapter = ArrayAdapter(
            requireActivity().applicationContext,
            R.layout.text_color_white,
            list
        )

        binding.butProgress.setOnClickListener {
            progress()
        }

        binding.List.setOnItemClickListener{_, view, i, _ ->

            when(i){
                0-> view.findNavController().navigate(R.id.ads_frag)
                1-> view.findNavController().navigate(R.id.orders_frag)
                2-> view.findNavController().navigate(R.id.posts_frag)
                3-> view.findNavController().navigate(R.id.reviews_frag)
                4-> view.findNavController().navigate(R.id.favorites_frag)
                5-> view.findNavController().navigate(R.id.coupons_frag)
                6-> view.findNavController().navigate(R.id.settings_JC)
            }
            list.clear()
        }

        return root
    }

    fun progress(){
        val intent = android.content.Intent(thiscontext!!, com.example.worldskillsshop.progress::class.java)
        startActivity(intent)
    }


    private fun DbDate(id:String):String{
        dbUser =  UserDBManager(thiscontext!!)
        dbUser?.openDb()
        NameUserDB = dbUser?.readDbDate(id)
        val Name = NameUserDB?.get(1)
        return Name.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbUser?.closeDb()
    }
}