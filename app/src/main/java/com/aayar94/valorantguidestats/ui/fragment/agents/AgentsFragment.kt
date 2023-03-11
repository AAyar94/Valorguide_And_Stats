package com.aayar94.valorantguidestats.ui.fragment.agents

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.R.color.valorant_blue
import com.aayar94.valorantguidestats.R.color.valorant_red
import com.aayar94.valorantguidestats.databinding.FragmentAgentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : Fragment() {
    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    private val adapter: AgentsFragmentAdapter by lazy {
        AgentsFragmentAdapter {
            val action = AgentsFragmentDirections.actionAgentsFragmentToAgentDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
    private val viewModel: AgentsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agentsRequest()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAgentsBinding.inflate(layoutInflater, container, false)
        binding.rvAgents.adapter = adapter
        binding.rvAgents.layoutManager = GridLayoutManager(context, 3)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitObserver()
    }

    private fun agentsRequest() {
        viewModel.getAgents()
    }

    private fun InitObserver() {
        viewModel._agents.observe(viewLifecycleOwner) {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}