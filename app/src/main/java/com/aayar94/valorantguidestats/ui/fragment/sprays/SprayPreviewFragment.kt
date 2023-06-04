package com.aayar94.valorantguidestats.ui.fragment.sprays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.valorantguidestats.databinding.FragmentSprayPreviewBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader

class SprayPreviewFragment(private val animationGif: String?) : Fragment() {
    private var mBinding: FragmentSprayPreviewBinding? = null
    private val binding get() = mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSprayPreviewBinding.inflate(layoutInflater, container, false)


        if (animationGif != null) {
            GlideImageLoader(requireContext(), animationGif, binding.peekView)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}