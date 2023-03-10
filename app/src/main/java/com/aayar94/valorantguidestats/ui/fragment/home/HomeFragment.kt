package com.aayar94.valorantguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.databinding.FragmentHomeBinding
import com.aayar94.valorantguidestats.ui.fragment.home.adapter.AgentsAdapter
import com.aayar94.valorantguidestats.ui.fragment.home.adapter.GameModesAdapter
import com.aayar94.valorantguidestats.ui.fragment.home.adapter.HomeWeaponsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var mBinding: FragmentHomeBinding? = null
    private val binding get() = mBinding!!
    private val agentsAdapter: AgentsAdapter by lazy {
        AgentsAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToAgentDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
    private val gamemodeAdapter: GameModesAdapter by lazy {
        GameModesAdapter({

        })
    }
    private val homeWeaponsAdapter by lazy { HomeWeaponsAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        agentsRequest()
        weaponsRequest()
        gamemodeRequest()

        binding.rvAgents.adapter = agentsAdapter
        binding.rvWeapons.adapter = homeWeaponsAdapter
        binding.rvGamemodes.adapter = gamemodeAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
    }

    fun initObserver() {
        viewModel._agents.observe(viewLifecycleOwner) {
            agentsAdapter.setData(it)
           // agentsAdapter.notifyDataSetChanged()
        }
        viewModel._weapons.observe(viewLifecycleOwner) {
            homeWeaponsAdapter.setData(it)
            //homeWeaponsAdapter.notifyDataSetChanged()
        }
        viewModel._gameMode.observe(viewLifecycleOwner) {
            gamemodeAdapter.setData(it)
            //gamemodeAdapter.notifyDataSetChanged()
        }
    }

    private fun gamemodeRequest() {
        viewModel.getGameMode()
    }

    private fun agentsRequest() {
        viewModel.getAgents()
    }

    private fun weaponsRequest() {
        viewModel.getWeapons()
    }
}