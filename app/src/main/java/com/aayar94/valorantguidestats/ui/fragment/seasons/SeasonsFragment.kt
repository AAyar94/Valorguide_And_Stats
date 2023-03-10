package com.aayar94.valorantguidestats.ui.fragment.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aayar94.valorantguidestats.databinding.FragmentSeasonsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonsFragment : Fragment() {
    private var _binding: FragmentSeasonsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeasonsViewModel by viewModels()
    private val adapter: SeasonsAdapter by lazy { SeasonsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSeasons()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeasonsBinding.inflate(layoutInflater, container, false)
        binding.seasonsRv.adapter = adapter
        viewModel.seasonList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        return binding.root
    }

}