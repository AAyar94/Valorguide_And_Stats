package com.aayar94.valorguidestats.ui.fragment.your_stats_preview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aayar94.valorguidestats.data.models.game_content.LevelBorders
import com.aayar94.valorguidestats.util.ResponseHandler
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.FragmentYourStatsPreviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
        viewModel.getUserStats(args.gamerName, args.tag)
        viewModel.getLevelBorders()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentYourStatsPreviewBinding.inflate(layoutInflater, container, false)

        binding.goBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.userMainStats.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Success -> {
                    if (response.data != null && response.data.status == 200) {
                        with(binding) {
                            progressCircular.visibility = View.GONE
                            goBackButton.visibility = View.GONE
                            errorImageView.visibility = View.GONE
                            errorTextView.visibility = View.GONE
                            nestedScrollView.visibility = View.VISIBLE
                            val userLevelBorder = levelBorderPicker(viewModel.userMainStats.value?.data?.data?.account_level)
                            Glide.with(root)
                                .load(userLevelBorder.smallPlayerCardAppearance)
                                .into(levelBorderImageView)
                            Glide.with(root)
                                .load(userLevelBorder.levelNumberAppearance)
                                .into(levelTextBg)
                            Glide.with(root)
                                .load(viewModel.userMainStats.value?.data?.data!!.card.wide)
                                .placeholder(R.drawable.ic_downloading_placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade()).fitCenter()
                                .into(bannerImage)
                            Glide.with(root)
                                .load(viewModel.userMainStats.value?.data!!.data.card.small)
                                .placeholder(R.drawable.ic_downloading_placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade()).fitCenter()
                                .into(profileImage)
                            levelText.text =
                                "${viewModel.userMainStats.value?.data!!.data.account_level}"
                            gamerTag.text = viewModel.userMainStats.value?.data!!.data.name
                            tagText.text = "#${viewModel.userMainStats.value?.data!!.data.tag}"

                            with(viewModel.userMainStats) {
                                viewModel.getUserMatchHistory(
                                    value!!.data!!.data.region, value!!.data!!.data.puuid
                                )
                                viewModel.getUserMMRChange(
                                    value!!.data!!.data.region, value!!.data!!.data.puuid
                                )
                            }
                        }
                    }
                }

                is ResponseHandler.Loading -> {
                    with(binding) {
                        nestedScrollView.visibility = View.GONE
                        progressCircular.visibility = View.VISIBLE
                    }
                }

                is ResponseHandler.Error -> {
                    with(binding) {
                        progressCircular.visibility = View.GONE
                        nestedScrollView.visibility = View.GONE
                        errorImageView.visibility = View.VISIBLE
                        errorTextView.visibility = View.VISIBLE
                        goBackButton.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.userMatchHistory.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseHandler.Loading -> {
                    with(binding) {
                        lastMatchesProgressBar.visibility = View.VISIBLE
                        lastMatchesRV.visibility = View.INVISIBLE
                    }
                }

                is ResponseHandler.Success -> {
                    with(binding) {
                        lastMatchesProgressBar.visibility = View.GONE
                        lastMatchesRV.visibility = View.VISIBLE
                        adapter.setData(it.data!!)
                        lastMatchesRV.adapter = adapter
                    }
                }

                is ResponseHandler.Error -> {}
            }

        }
        viewModel.userMMRChange.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Loading -> {
                    with(binding) {
                        progressBarMMRChange.visibility = View.VISIBLE
                        imageRankDown.visibility = View.INVISIBLE
                        imageRankUp.visibility = View.INVISIBLE
                        currentRankName.visibility = View.INVISIBLE
                        currentRankImage.visibility = View.INVISIBLE
                    }
                }

                is ResponseHandler.Success -> {
                    with(binding) {
                        Glide.with(currentRankImage.context).load(response.data!!.data.images.large)
                            .into(currentRankImage)
                        currentRankName.text = response.data.data.currenttierpatched
                        currentMMRChangeText.text =
                            response.data.data.mmr_change_to_last_game.toString()
                        if (response.data.data.mmr_change_to_last_game > 0) {
                            imageRankUp.visibility = View.VISIBLE
                            imageRankDown.visibility = View.INVISIBLE
                        } else if (response.data.data.mmr_change_to_last_game < 0) {
                            imageRankUp.visibility = View.INVISIBLE
                            imageRankDown.visibility = View.VISIBLE
                        } else {
                            imageRankUp.visibility = View.INVISIBLE
                            imageRankDown.visibility = View.INVISIBLE
                        }
                        imageRankUp.apply {
                            Glide.with(this.context).load(response.data.data.images.triangle_up)
                                .into(this)
                        }
                        imageRankDown.apply {
                            Glide.with(imageRankDown.context)
                                .load(response.data.data.images.triangle_down).into(imageRankDown)
                        }
                    }
                }

                is ResponseHandler.Error -> {}
            }
        }

        binding.logoutButton.setOnClickListener {
            statsLogout()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun levelBorderPicker(accountLevel: Int?): LevelBorders {
        val levelBorders = viewModel.levelBorders
        when (accountLevel) {
            in 1..20 -> return levelBorders.value!![1]
            in 21..40 -> return levelBorders.value!![2]
            in 41..60 -> return levelBorders.value!![3]
            in 61..80 -> return levelBorders.value!![4]
            in 81..100 -> return levelBorders.value!![5]
            in 101..120 -> return levelBorders.value!![6]
            in 121..140 -> return levelBorders.value!![7]
            in 141..160 -> return levelBorders.value!![8]
            in 161..180 -> return levelBorders.value!![9]
            in 181..200 -> return levelBorders.value!![10]
            in 201..220 -> return levelBorders.value!![11]
            in 221..240 -> return levelBorders.value!![12]
            in 241..260 -> return levelBorders.value!![13]
            in 261..280 -> return levelBorders.value!![14]
            in 281..300 -> return levelBorders.value!![15]
            in 301..320 -> return levelBorders.value!![16]
            in 321..340 -> return levelBorders.value!![17]
            in 341..360 -> return levelBorders.value!![18]
            in 361..380 -> return levelBorders.value!![19]
            in 381..400 -> return levelBorders.value!![20]
            in 401..420 -> return levelBorders.value!![21]
            in 421..440 -> return levelBorders.value!![22]
            in 441..460 -> return levelBorders.value!![23]
            in 461..480 -> return levelBorders.value!![24]
            else -> return levelBorders.value!![1]
        }
    }

    private fun statsLogout() {
        viewModel.deleteUserGamerTagAndTag(requireContext())
        findNavController().navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}