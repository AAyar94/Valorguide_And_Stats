package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentUserLastMatchesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class UserLastMatchesFragment : Fragment() {
    private var _binding: FragmentUserLastMatchesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MatchDetailsViewModel by viewModels()
    private val args: UserLastMatchesFragmentArgs by navArgs()
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
        _binding = FragmentUserLastMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.matchDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    mapNameText.text = "${getString(R.string.map)}${it.data.metadata.map}"
                    gameModeText.text = "${R.string.game_mode} ${it.data.metadata.mode}"
                    roundsPlayedText.text =
                        "${getString(R.string.rounds_played)}${it.data.metadata.rounds_played}"
                    gameLengthText.text = msToLocalTimeString(it.data.metadata.game_length)
                    startTimeText.text = msToLocalTimeString(it.data.metadata.game_start)
                }
                with(binding) {
                    teamRedWinRoundText.text = it.data.teams.red.rounds_won.toString()
                    teamBlueWinRaundText.text = it.data.teams.blue.rounds_won.toString()
                    teamRedHeader.text = getString(R.string.team_red)
                    teamBlueHeader.text = getString(R.string.team_blue)
                }
                with(binding) {
                    teamRedAdapter.setData(it.data.players.red)
                    teamBlueAdapter.setData(it.data.players.blue)
                    roundStateAdapter.setData(it.data.rounds)
                    teamBlueRV.adapter = teamBlueAdapter
                    teamRedRV.adapter = teamRedAdapter
                    roundsWinState.adapter = roundStateAdapter
                }
            }
        }
    }

    private fun msToLocalTimeString(ms: Int): String {
        val instant = Instant.ofEpochMilli(ms.toLong())
        val zoneId = ZoneId.systemDefault()
        val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
        val formatter = DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy")
        return localDateTime.format(formatter)
    }


    private fun convertMsToMinute(gameLength: Int): String {
        return (gameLength.toDouble() / (1000 * 60)).toString()
    }
}