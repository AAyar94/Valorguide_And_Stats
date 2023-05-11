package com.aayar94.valorantguidestats.ui.fragment.your_stats_preview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsPreviewBinding
import com.aayar94.valorantguidestats.util.ResponseHandler
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourStatsPreviewFragment : Fragment() {
    private var _binding: FragmentYourStatsPreviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: YourStatsPreviewViewModel by viewModels()
    private val args: YourStatsPreviewFragmentArgs by navArgs()
    private val adapter: LastMatchesAdapter by lazy {
        LastMatchesAdapter {
            val action =
                YourStatsPreviewFragmentDirections.actionYourStatsPreviewFragmentToUserLastMatchesFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            viewModel.getUserStats(args.gamerName, args.tag)
        } catch (e: Exception) {
            statsLogout()
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYourStatsPreviewBinding.inflate(layoutInflater, container, false)

        viewModel.userMainStats.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    Glide.with(binding.root)
                        .load(viewModel.userMainStats.value?.data?.data!!.card.wide)
                        .fitCenter()
                        .into(binding.bannerImage)
                    Glide.with(binding.root)
                        .load(viewModel.userMainStats.value?.data!!.data.card.small)
                        .fitCenter()
                        .into(binding.profileImage)
                    binding.levelText.text =
                        "${viewModel.userMainStats.value?.data!!.data.account_level}Level"
                    binding.gamerTag.text = viewModel.userMainStats.value?.data!!.data.name
                    binding.tagText.text = "#${viewModel.userMainStats.value?.data!!.data.tag}"

                    viewModel.getUserMatchHistory(
                        viewModel.userMainStats.value!!.data!!.data.region,
                        viewModel.userMainStats.value!!.data!!.data.puuid
                    )
                    viewModel.getUserMMRChange(
                        viewModel.userMainStats.value!!.data!!.data.region,
                        viewModel.userMainStats.value!!.data!!.data.puuid
                    )
                }

                is ResponseHandler.Loading -> {
                    binding.nestedScrollView.visibility = View.GONE
                    binding.progressCircular.visibility = View.VISIBLE
                }

                is ResponseHandler.Error -> {}

            }

        }
        binding.logoutButton.setOnClickListener {
            statsLogout()
        }
        viewModel.userMatchHistory.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Loading -> {
                    binding.lastMatchesProgressBar.visibility = View.VISIBLE
                    binding.lastMatchesRV.visibility = View.INVISIBLE
                }

                is ResponseHandler.Success -> {
                    binding.lastMatchesProgressBar.visibility=View.GONE
                    binding.lastMatchesRV.visibility=View.VISIBLE
                    adapter.setData(response.data!!)
                    binding.lastMatchesRV.adapter = adapter
                }

                is ResponseHandler.Error -> {}
            }

        }
        viewModel.userMMRChange.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Loading -> {
                    binding.progressBarMMRChange.visibility = View.VISIBLE
                    binding.imageRankDown.visibility = View.INVISIBLE
                    binding.imageRankUp.visibility = View.INVISIBLE
                    binding.currentRankName.visibility = View.INVISIBLE
                    binding.currentRankImage.visibility = View.INVISIBLE
                }

                is ResponseHandler.Success -> {
                    binding.currentRankImage.apply {
                        Glide.with(this.context)
                            .load(response.data!!.data.images.large)
                            .into(this)
                    }
                    binding.currentRankName.text = response.data!!.data.currenttierpatched
                    binding.currentMMRChangeText.text =
                        response.data.data.mmr_change_to_last_game.toString()
                    if (response.data.data.mmr_change_to_last_game > 0) {
                        binding.imageRankUp.visibility = View.VISIBLE
                        binding.imageRankDown.visibility = View.INVISIBLE
                    } else if (response.data.data.mmr_change_to_last_game < 0) {
                        binding.imageRankUp.visibility = View.INVISIBLE
                        binding.imageRankDown.visibility = View.VISIBLE
                    } else {
                        binding.imageRankUp.visibility = View.INVISIBLE
                        binding.imageRankDown.visibility = View.INVISIBLE
                    }
                    binding.imageRankUp.apply {
                        Glide.with(this.context)
                            .load(response.data.data.images.triangle_up)
                            .into(this)
                    }
                    binding.imageRankDown.apply {
                        Glide.with(this.context)
                            .load(response.data.data.images.triangle_down)
                            .into(this)
                    }
                }

                is ResponseHandler.Error -> {}
            }
        }
        return binding.root
    }

    private fun statsLogout() {
        val sharedPref =
            activity?.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val sharedPrefEditor = sharedPref?.edit()
        sharedPrefEditor?.putString("gamerTag", "")
        sharedPrefEditor?.putString("tag", "")
        sharedPrefEditor?.apply()
        findNavController().navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}