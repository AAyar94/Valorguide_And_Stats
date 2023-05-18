package com.aayar94.valorantguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.valorantguidestats.databinding.ViewPagerLayoutAgentRoleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentRoleFragment(
    private val agentClass: String,
    private val agentClassDetails: String
) : Fragment() {
    private var mBinding: ViewPagerLayoutAgentRoleBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = ViewPagerLayoutAgentRoleBinding.inflate(layoutInflater, container, false)
        with(binding) {
            agentClassName.text = agentClass
            agentClassDesc.text = agentClassDetails
        }
        return binding.root
    }

}