package com.aayar94.valorantguidestats.ui.fragment.your_stats_preview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsPreviewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourStatsPreviewFragment : Fragment() {
    private var _binding: FragmentYourStatsPreviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: YourStatsPreviewViewModel by viewModels()
    private val args: YourStatsPreviewFragmentArgs by navArgs()
    private val adapter: LastMatchesAdapter by lazy { LastMatchesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserStats(args.gamerName, args.tag)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYourStatsPreviewBinding.inflate(layoutInflater, container, false)


        viewModel.userMainStats.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .load(viewModel.userMainStats.value?.data?.card?.wide)
                .fitCenter()
                .into(binding.bannerImage)

            Glide.with(binding.root)
                .load(viewModel.userMainStats.value?.data?.card?.small)
                .fitCenter()
                .into(binding.profileImage)

            binding.levelText.text = "${viewModel.userMainStats.value?.data!!.account_level}Level"
            binding.gamerTag.text = viewModel.userMainStats.value?.data!!.name
            binding.tagText.text = "#${viewModel.userMainStats.value?.data!!.tag}"

            viewModel.getUserMatchHistory(
                viewModel.userMainStats.value!!.data.region,
                viewModel.userMainStats.value!!.data.puuid
            )
            viewModel.getUserMMRChange(
                viewModel.userMainStats.value!!.data.region,
                viewModel.userMainStats.value!!.data.puuid
            )

        }

        binding.logoutButton.setOnClickListener {
            statsLogout()
        }

        viewModel.userMatchHistory.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setData(it)
            }
            binding.lastMatchesRV.adapter = adapter
        }
        viewModel.userMMRChange.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.currentRankImage.apply {
                    Glide.with(this.context)
                        .load(it.data.images.large)
                        .into(this)
                }
                binding.currentRankName.text = it.data.currenttierpatched
                binding.currentMMRChangeText.text = it.data.mmr_change_to_last_game.toString()
                if (it.data.mmr_change_to_last_game>0){
                    binding.imageRankUp.visibility=View.VISIBLE
                    binding.imageRankDown.visibility=View.INVISIBLE
                }else if (it.data.mmr_change_to_last_game<0){
                    binding.imageRankUp.visibility=View.INVISIBLE
                    binding.imageRankDown.visibility=View.VISIBLE
                }else{
                    binding.imageRankUp.visibility=View.INVISIBLE
                    binding.imageRankDown.visibility=View.INVISIBLE
                }
                binding.imageRankUp.apply {
                    Glide.with(this.context)
                        .load(it.data.images.triangle_up)
                        .into(this)
                }
                binding.imageRankDown.apply {
                    Glide.with(this.context)
                        .load(it.data.images.triangle_down)
                        .into(this)
                }
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

}