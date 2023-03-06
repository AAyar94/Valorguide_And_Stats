package com.aayar94.valorantguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.databinding.FragmentHomeBinding
import com.aayar94.valorantguidestats.ui.fragment.home.adapter.AgentsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var mBinding: FragmentHomeBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy {
        AgentsAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToAgentsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.rvAgents.adapter = adapter.also {
            val agent3 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val agent2 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val agent1 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val agent4 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val agent5 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val agent6 = Agent(
                1.toString(),
                "Fade",
                "Turkish bounty hunter",
                "BountyHunter",
                null,
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/fullportrait.png",
                "ShooterGame/Content/Characters/BountyHunter/BountyHunter_PrimaryAsset",
                false,
                true, true,
                role = null, null
            )
            val list = mutableListOf<Agent>(agent1, agent2, agent3, agent4, agent5, agent6)
            it.setData(list)
            it.notifyDataSetChanged()
        }


        return binding.root
    }

}