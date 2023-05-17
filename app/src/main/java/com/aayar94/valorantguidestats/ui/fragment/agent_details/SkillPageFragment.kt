package com.aayar94.valorantguidestats.ui.fragment.agent_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.valorantguidestats.databinding.ViewPagerLayoutAgentSkillsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkillPageFragment(private val skillName: String, private val skillDesc: String) : Fragment() {
    private var _binding: ViewPagerLayoutAgentSkillsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewPagerLayoutAgentSkillsBinding.inflate(layoutInflater, container, false)
        binding.skillName.text = skillName
        binding.skillDesc.text = skillDesc
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}