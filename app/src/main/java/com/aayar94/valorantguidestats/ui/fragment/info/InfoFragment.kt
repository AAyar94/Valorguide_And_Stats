package com.aayar94.valorantguidestats.ui.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        with(viewModel) {
            getMapBackground()
            getWeaponBackground()
            getStatBackground()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        with(binding) {
            with(viewModel) {
                /**     OBSERVERS       */
                mapImage.observe(viewLifecycleOwner) {
                    currentMap = it
                    GlideImageLoader(requireContext(), currentMap, mapsIv)
                }
                weaponImage.observe(viewLifecycleOwner) {
                    GlideImageLoader(requireContext(), it, weaponsIv)
                }
                bundleImage.observe(viewLifecycleOwner) {
                    GlideImageLoader(requireContext(), it, bundlesIv)
                }
                statImage.observe(viewLifecycleOwner) {
                    if (it != null) {
                        GlideImageLoader(requireContext(), it, statsIv)
                    }
                }
                /**     CLICK LISTENERS         */
                seasonsCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToSeasonsFragment()
                    findNavController().navigate(action)
                }
                weaponsCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToWeaponsFragment()
                    findNavController().navigate(action)
                }
                mapsCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToMapsFragment()
                    findNavController().navigate(action)
                }
                statsCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToStatsFragment()
                    findNavController().navigate(action)
                }
                bundlesCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToBundlesFragment()
                    findNavController().navigate(action)
                }
                sprayImage.observe(viewLifecycleOwner) {
                    if (it != null) {
                        GlideImageLoader(requireContext(), it, spraysImageView)
                    }
                }
                spraysCardView.setOnClickListener {
                    val action = InfoFragmentDirections.actionInfoFragmentToSpraysFragment()
                    findNavController().navigate(action)
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}