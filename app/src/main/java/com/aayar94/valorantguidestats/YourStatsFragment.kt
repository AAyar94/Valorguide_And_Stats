package com.aayar94.valorantguidestats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsBinding

class YourStatsFragment : Fragment() {
    private var _binding : FragmentYourStatsBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentYourStatsBinding.inflate(layoutInflater,container,false)



        return binding.root
    }

}