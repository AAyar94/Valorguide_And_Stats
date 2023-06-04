package com.aayar94.valorantguidestats.ui.fragment.sprays

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentSpraysBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("InflateParams")
@AndroidEntryPoint
class SpraysFragment : Fragment() {
    private var mBinding: FragmentSpraysBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: SprayViewModel by viewModels()
    private val adapter: SpraysAdapter by lazy {
        SpraysAdapter {sprayLink->
            val builder = MaterialAlertDialogBuilder(requireContext())
            val inflater = LayoutInflater.from(requireContext())
            val view = inflater.inflate(R.layout.fragment_spray_preview, null)
            val imageView = view.findViewById<ImageView>(R.id.peekView)
            GlideImageLoader(requireContext(),sprayLink,imageView)
            builder.setView(view)
            builder.show()
        }
    }

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