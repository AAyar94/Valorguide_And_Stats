package com.aayar94.valorantguidestats.ui.fragment.sprays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aayar94.valorantguidestats.databinding.FragmentSpraysBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpraysFragment : Fragment() {
    private var mBinding: FragmentSpraysBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: SprayViewModel by viewModels()
    private val adapter: SpraysAdapter by lazy { SpraysAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSpraysBinding.inflate(layoutInflater, container, false)

        binding.spraysRecyclerView.adapter = adapter

        viewModel.sprays.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSprays()
    }
}