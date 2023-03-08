package com.aayar94.valorantguidestats.ui.fragment.agent_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.aayar94.valorantguidestats.databinding.FragmentAgentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailsFragment : Fragment() {
    private var _binding: FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: AgentDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailsBinding.inflate(layoutInflater, container, false)
        setAgentDetails()
        return binding.root
    }

    private fun setAgentDetails() {
        with(binding) {
            ivAgent.load(args.agent.fullPortrait)
            tvAgentName.text = args.agent.displayName
            tvDesc.text = args.agent.description
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}