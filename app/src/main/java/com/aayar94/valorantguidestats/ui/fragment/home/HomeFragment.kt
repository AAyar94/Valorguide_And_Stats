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
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentHomeBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.VALORANT_URL
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
    val agentRoleListFragment = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        agentsRequest()

        binding.rvAgents.adapter = agentsAdapter
        binding.webPageButton.setOnClickListener {
            val url = VALORANT_URL
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        setupTabsAndViewPager()

        return binding.root
    }

    private fun setupTabsAndViewPager() {
        agentRoleListFragment.clear()
        agentRoleListFragment.add(
            AgentRoleFragment(
                getString(R.string.sentinel),
                getString(R.string.sentinel_desc)
            )
        )
        agentRoleListFragment.add(
            AgentRoleFragment(
                getString(R.string.controller),
                getString(R.string.controller_desc)
            )
        )
        agentRoleListFragment.add(
            AgentRoleFragment(
                getString(R.string.initiator),
                getString(R.string.initator_desc)
            )
        )
        agentRoleListFragment.add(
            AgentRoleFragment(
                getString(R.string.duelist),
                getString(R.string.duelist_desc)
            )
        )

        val viewPagerAdapter = AgentRoleViewPagerAdapter(
            agentRoleListFragment, childFragmentManager
        )
        binding.classViewPager.adapter = viewPagerAdapter
        binding.agentClassTabLayout.setupWithViewPager(binding.classViewPager)
        binding.agentClassTabLayout.getTabAt(0)?.setIcon(R.drawable.sentinel_logo)
        binding.agentClassTabLayout.getTabAt(1)?.setIcon(R.drawable.contoller_logo)
        binding.agentClassTabLayout.getTabAt(2)?.setIcon(R.drawable.initiator_logo)
        binding.agentClassTabLayout.getTabAt(3)?.setIcon(R.drawable.duelist_logo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
    }

    private fun initObserver() {
        viewModel._agents.observe(viewLifecycleOwner) {
            agentsAdapter.setData(it)
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