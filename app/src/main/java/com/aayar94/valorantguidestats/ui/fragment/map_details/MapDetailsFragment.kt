package com.aayar94.valorantguidestats.ui.fragment.map_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentMapDetailsBinding


class MapDetailsFragment : Fragment() {
    private var _binding: FragmentMapDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MapDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapDetailsBinding.inflate(layoutInflater, container, false)
        binding.imgMap.load(args.ValorantMap.splash)
        binding.mapName.text = args.ValorantMap.displayName
        binding.mapCoordinatesText.text =
            getString(R.string.cordinates) + args.ValorantMap.coordinates
        binding.cordinateView.load(args.ValorantMap.displayIcon)
        //binding.calloutView.load(args.ValorantMap.)
        return binding.root
    }

}