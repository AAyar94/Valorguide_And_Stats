package com.aayar94.valorantguidestats.ui.fragment.weapon_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentWeaponSkinBinding

class WeaponSkinFragment(private val imageLink: String) : Fragment() {
    private var _binding: FragmentWeaponSkinBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeaponSkinBinding.inflate(layoutInflater, container, false)

        binding.weaponSkinImage.load(imageLink)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}