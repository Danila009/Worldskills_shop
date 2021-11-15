package com.example.worldskillsshop

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.worldskillsshop.databinding.FragmentDescriptionBinding
import com.example.worldskillsshop.databinding.FragmentHomeBinding

class description : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(requireContext(), R.layout.category_item, category)
        binding.category.setAdapter(arrayAdapterCategory)

        val sellers = resources.getStringArray(R.array.Sellers)
        val arrayAdapterSellers = ArrayAdapter(requireContext(), R.layout.category_item, sellers)
        binding.sellers.setAdapter(arrayAdapterSellers)

        val Sorting = resources.getStringArray(R.array.Sellers)
        val arrayAdapterSorting = ArrayAdapter(requireContext(), R.layout.category_item, Sorting)
        binding.sellers.setAdapter(arrayAdapterSorting)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = "Уточнить"
        setHasOptionsMenu(true)

        return root
    }
}