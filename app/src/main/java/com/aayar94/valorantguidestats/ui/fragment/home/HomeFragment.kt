package com.aayar94.valorantguidestats.ui.fragment.home

import android.content.Intent homeimport android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.databinding.FragmentHomeBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.VALORANT_URL
import com.google.android.material.carousel.CarouselLayoutManager
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        agentsRequest()

        binding.rvAgents.adapter = agentsAdapter
        binding.webPageButton.setOnClickListener {
            val url = VALORANT_URL
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
    }

    fun initObserver() {
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