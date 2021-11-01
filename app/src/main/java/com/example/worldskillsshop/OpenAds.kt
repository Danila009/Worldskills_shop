package com.example.worldskillsshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.worldskillsshop.databinding.FragmentHomeBinding
import com.example.worldskillsshop.databinding.FragmentOpenAdsBinding

class OpenAds : Fragment() {
    var bul = false

    private var _binding: FragmentOpenAdsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpenAdsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.heartBut.setOnClickListener {

            when(bul){

                false-> {
                    bul = true
                    binding.heartBut.setImageResource(R.drawable.heart_act)
                }
                true-> {
                    bul = false
                    binding.heartBut.setImageResource(R.drawable.heart)
                }
            }
        }

        binding.messageBut.setOnClickListener {

            view?.findNavController()?.navigate(R.id.posts_frag)
        }
        return root
    }
}