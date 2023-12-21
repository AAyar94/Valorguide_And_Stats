package com.aayar94.valorantguidestats.ui.fragment.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.util.Constants.VALORANT_URL
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val agentsAdapter: AgentsAdapter by lazy {
        AgentsAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToAgentDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
    private val viewModel: HomeViewModel by viewModels()
    private val agentRoleListFragment = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        agentsRequest()

        with(binding) {
            rvAgents.adapter = agentsAdapter
            webPageButton.setOnClickListener {
                val url = VALORANT_URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }

        setupTabsAndViewPager()

        return binding.root
    }

    private fun setupTabsAndViewPager() {
        with(agentRoleListFragment) {
            clear()
            add(
                AgentRoleFragment(
                    getString(R.string.sentinel),
                    getString(R.string.sentinel_desc)
                )
            )
            add(
                AgentRoleFragment(
                    getString(R.string.controller),
                    getString(R.string.controller_desc)
                )
            )
            add(
                AgentRoleFragment(
                    getString(R.string.initiator),
                    getString(R.string.initator_desc)
                )
            )
            add(
                AgentRoleFragment(
                    getString(R.string.duelist),
                    getString(R.string.duelist_desc)
                )
            )
        }
        val viewPagerAdapter = AgentRoleViewPagerAdapter(
            agentRoleListFragment, childFragmentManager
        )

        with(binding) {
            classViewPager.adapter = viewPagerAdapter
            agentClassTabLayout.setupWithViewPager(binding.classViewPager)
            agentClassTabLayout.getTabAt(0)?.setIcon(R.drawable.sentinel_logo)
            agentClassTabLayout.getTabAt(1)?.setIcon(R.drawable.contoller_logo)
            agentClassTabLayout.getTabAt(2)?.setIcon(R.drawable.initiator_logo)
            agentClassTabLayout.getTabAt(3)?.setIcon(R.drawable.duelist_logo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
    }

    private fun initObserver() {
        viewModel.agents.observe(viewLifecycleOwner) {
            agentsAdapter.submitList(it)
        }
    }

    private fun agentsRequest() {
        viewModel.getAgents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}