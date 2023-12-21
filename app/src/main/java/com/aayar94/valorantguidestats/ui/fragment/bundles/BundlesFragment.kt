package com.aayar94.valorantguidestats.ui.fragment.bundles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aayar94.valorantguidestats.databinding.FragmentBundlesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BundlesFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentBundlesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BundlesViewModel by viewModels()
    private val bundlesAdapter: BundlesFragmentAdapter by lazy { BundlesFragmentAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBundles()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBundlesBinding.inflate(layoutInflater, container, false)
        viewModel.bundles.observe(viewLifecycleOwner) { list ->
            bundlesAdapter.submitList(list)
        }
        binding.bundlesRecyclerView.adapter = bundlesAdapter

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}