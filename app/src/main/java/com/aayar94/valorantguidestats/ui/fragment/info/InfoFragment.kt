package com.aayar94.valorantguidestats.ui.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentInfoBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InfoViewModel by viewModels()
    private var currentMap: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMapBackground()
        viewModel.getWeaponBackground()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        viewModel.mapImage.observe(viewLifecycleOwner) {
            currentMap = it
            GlideImageLoader(requireContext(),currentMap,binding.mapsIv)
        }
        binding.mapsCardView.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMapsFragment()
            findNavController().navigate(action)
        }
        binding.seasonsCardView.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToSeasonsFragment()
            findNavController().navigate(action)
        }
        viewModel.weaponImage.observe(viewLifecycleOwner) {
            GlideImageLoader(requireContext(),it,binding.weaponsIv)
        }
        binding.weaponsCardView.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToWeaponsFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}