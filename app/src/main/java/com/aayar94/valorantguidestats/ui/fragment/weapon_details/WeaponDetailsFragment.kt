package com.aayar94.valorantguidestats.ui.fragment.weapon_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.aayar94.valorantguidestats.databinding.FragmentWeaponDetailsBinding
import kotlin.math.roundToInt


class WeaponDetailsFragment : Fragment() {
    private var _binding: FragmentWeaponDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: WeaponDetailsFragmentArgs by navArgs()

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeaponDetailsBinding.inflate(layoutInflater, container, false)
        with(binding) {
            weaponDetailImage.load(args.weapon.displayIcon)
            weaponDetailName.text = args.weapon.displayName
        }
        with(binding.progressBarHeadDamage) {
            progress = args.weapon.weaponStats.damageRanges[0].headDamage.roundToInt()
            isClickable = false
        }


        return binding.root
    }

}