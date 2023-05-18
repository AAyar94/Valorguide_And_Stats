package com.aayar94.valorantguidestats.ui.fragment.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.databinding.FragmentWeaponsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeaponsFragment : Fragment() {
    private var _binding: FragmentWeaponsBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        WeaponsFragmentRowAdapter {
            val action = WeaponsFragmentDirections.actionWeaponsFragmentToWeaponDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
    private val viewModel: WeaponsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getWeapons()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponsBinding.inflate(layoutInflater, container, false)
        binding.weaponsListRV.adapter = adapter
        viewModel.weaponList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}