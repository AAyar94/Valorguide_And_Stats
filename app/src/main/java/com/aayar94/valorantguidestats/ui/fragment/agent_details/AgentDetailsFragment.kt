package com.aayar94.valorantguidestats.ui.fragment.agent_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.databinding.FragmentAgentDetailsBinding

class AgentDetailsFragment : Fragment() {
    private var _binding: FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: AgentDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root



    }

}