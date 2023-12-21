package com.aayar94.valorguidestats.ui.fragment.match_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aayar94.valorguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorguidestats.util.ResponseHandler
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.FragmentUserMatchDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class UserMatchDetailsFragment : Fragment() {
    private var _binding: FragmentUserMatchDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MatchDetailsViewModel by viewModels()
    private val args: UserMatchDetailsFragmentArgs by navArgs()
    private val teamRedAdapter: TeamRedRVAdapter by lazy { TeamRedRVAdapter() }
    private val teamBlueAdapter: TeamBlueRVAdapter by lazy { TeamBlueRVAdapter() }
    private val roundStateAdapter: RoundStateRVAdapter by lazy { RoundStateRVAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMatchDetails(args.matchID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserMatchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.matchDetails.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResponseHandler.Success -> {
                    if (response.data != null) {
                        loadContentToScreen(response)
                        hideError()
                        hideProgressBar()
                        showContent()
                    }
                }

                is ResponseHandler.Error -> {
                    hideContent()
                    hideProgressBar()
                    showError()
                }

                is ResponseHandler.Loading -> {
                    hideContent()
                    hideError()
                    showProgressBar()
                }
            }
        }
    }

    private fun hideError() {
        binding.errorImageView.visibility = View.INVISIBLE
    }

    private fun showError() {
        binding.errorImageView.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.nestedScrollView.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideContent() {
        binding.nestedScrollView.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun loadContentToScreen(response: ResponseHandler.Success<UserMatchDetailDataModel>) {
        with(binding) {
            mapNameText.text =
                "${getString(R.string.map)}${response.data!!.data.metadata.map}"
            gameModeText.text =
                "${getString(R.string.game_mode)} ${
                    gameModeTranslator(
                        response.data.data.metadata.mode
                    )
                }"
            roundsPlayedText.text =
                "${getString(R.string.rounds_played)} ${response.data.data.metadata.rounds_played}"
            gameLengthText.text =
                "${getString(R.string.game_length)}${formatGameLength(response.data.data.metadata.game_length)}"
            startTimeText.text =
                formatTimestamp(response.data.data.metadata.game_start.toLong())


            teamRedWinRoundText.text =
                response.data.data.teams.red.rounds_won.toString()
            teamBlueWinRaundText.text =
                response.data.data.teams.blue.rounds_won.toString()
            teamRedHeader.text = getString(R.string.team_red)
            teamBlueHeader.text = getString(R.string.team_blue)


            teamRedAdapter.submitList(response.data.data.players.red)
            teamBlueAdapter.submitList(response.data.data.players.blue)
            roundStateAdapter.submitList(response.data.data.rounds)
            teamBlueRV.adapter = teamBlueAdapter
            teamRedRV.adapter = teamRedAdapter
            roundsWinState.adapter = roundStateAdapter
        }
    }

    private fun gameModeTranslator(gameMode: String): String {
        return when (gameMode) {
            "Competitive" -> binding.root.context.getString(R.string.competitive)
            "Unrated" -> binding.root.context.getString(R.string.unrated)
            "Premier" -> binding.root.context.getString(R.string.premier)
            "Deathmatch" -> binding.root.context.getString(R.string.deathmatch)
            "Escalation" -> binding.root.context.getString(R.string.escalation)
            "Onboarding" -> binding.root.context.getString(R.string.onboarding)
            "Replication" -> binding.root.context.getString(R.string.replication)
            "Spike Rush" -> binding.root.context.getString(R.string.spike_rush)
            "PRACTICE" -> binding.root.context.getString(R.string.practice)
            "Snowball Fight" -> binding.root.context.getString(R.string.snowball_fight)
            "Swiftplay" -> binding.root.context.getString(R.string.swiftplay)
            "Team Deathmatch" -> binding.root.context.getString(R.string.team_deathmatch)
            else -> {
                ""
            }
        }
    }

    private fun formatGameLength(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return getString(R.string._02d_m_02d_s).format(minutes, remainingSeconds)
    }

    private fun formatTimestamp(timestamp: Long): String {
        val millis = timestamp * 1000
        val date = Date(millis)
        val dateFormat = SimpleDateFormat("hh:mm dd/MMM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
