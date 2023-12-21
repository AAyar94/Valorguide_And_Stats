package com.aayar94.valorantguidestats.ui.fragment.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorguidestats.databinding.FragmentMapsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MapsViewModel by viewModels()
    private val adapter: MapsAdapter by lazy {
        MapsAdapter { map, extra ->
            val action = MapsFragmentDirections.actionMapsFragmentToMapDetailsFragment(map)
            findNavController().navigate(action, extra)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(layoutInflater, container, false)
        initObserver()
        binding.rvMaps.adapter = adapter
        viewModel.getMaps()
        return binding.root
    }

    private fun initObserver() {
        viewModel.mapList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}