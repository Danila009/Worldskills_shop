package com.example.worldskillsshop.ViewPager

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import com.example.worldskillsshop.R
import com.example.worldskillsshop.databinding.FragmentAddingAdsBinding
import com.example.worldskillsshop.databinding.ViewPagerImageBinding

const val IMAGEVP_1 = ""
const val IMAGEVP_2 = ""
const val IMAGEVP_3 = ""
const val IMAGEVP_4 = ""

class ViewPager_image : Fragment() {

    private var _binding:ViewPagerImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        _binding = ViewPagerImageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        arguments?.takeIf { it.containsKey(IMAGEVP_1) }?.apply{
            binding.imageView4.setImageURI(getString(IMAGEVP_1)?.toUri())
        }
        arguments?.takeIf { it.containsKey(IMAGEVP_2) }?.apply{
            binding.imageView4.setImageURI(getString(IMAGEVP_2)?.toUri())
        }
        arguments?.takeIf { it.containsKey(IMAGEVP_3) }?.apply{
            binding.imageView4.setImageURI(getString(IMAGEVP_3)?.toUri())
        }
        arguments?.takeIf { it.containsKey(IMAGEVP_4) }?.apply{
            binding.imageView4.setImageURI(getString(IMAGEVP_4)?.toUri())
        }
    }
}