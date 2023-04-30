package com.aayar94.valorantguidestats.ui.fragment.your_stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsPreviewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourStatsPreviewFragment : Fragment() {
    private var _binding: FragmentYourStatsPreviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: YourStatsViewModel by viewModels()
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

        }

        viewModel.userMatchHistory.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setData(it)
            }
            binding.lastMatchesRV.adapter = adapter
        }

        return binding.root
    }

}