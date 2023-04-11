package com.aayar94.valorantguidestats.ui.fragment.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aayar94.valorantguidestats.databinding.FragmentMapsBinding
import com.google.android.material.transition.MaterialElevationScale
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
        binding.rvMaps.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    private fun initObserver() {
        viewModel._mapList.observe(viewLifecycleOwner) {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}