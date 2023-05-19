package com.aayar94.valorantguidestats.ui.fragment.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.aayar94.valorantguidestats.databinding.FragmentStatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { StatsAdapter() }
    private val viewModel: StatsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        binding.statsRV.adapter = adapter

        viewModel.getTiers()
        viewModel.tiersList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}