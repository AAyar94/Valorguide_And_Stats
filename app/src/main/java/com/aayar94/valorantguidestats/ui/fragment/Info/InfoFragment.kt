package com.aayar94.valorantguidestats.ui.fragment.Info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentInfoBinding
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        viewModel.mapImage.observe(viewLifecycleOwner) {
            currentMap = it
            binding.mapsIv.load(currentMap) {
                placeholder(R.drawable.hourglass)
                crossfade(true)
            }
            binding.mapsCardView.setOnClickListener {
                val action = InfoFragmentDirections.actionInfoFragmentToMapsFragment()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}