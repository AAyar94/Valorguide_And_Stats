package com.aayar94.valorantguidestats.ui.fragment.your_stats

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourStatsFragment : Fragment() {
    private var _binding: FragmentYourStatsBinding? = null
    private val binding get() = _binding!!
    private var gamerTag: String? = null
    private var tag: String? = null

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYourStatsBinding.inflate(layoutInflater, container, false)

        val sharedPref =
            activity?.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val sharedPrefEditor = sharedPref?.edit()
        gamerTag = sharedPref?.getString("gamerTag", null)
        tag = sharedPref?.getString("tag", null)

        if (!gamerTag.isNullOrBlank() && !tag.isNullOrBlank()) {
            val action =
                YourStatsFragmentDirections.actionYourStatsFragmentToYourStatsPreviewFragment(
                    gamerTag!!,
                    tag!!
                )
            findNavController().navigate(action)
        }


        binding.submitButton.setOnClickListener {
            val tag = binding.tagTextField.editText?.text.toString()
            val gamerTag = binding.accountTextField.editText?.text.toString()
            sharedPrefEditor?.putString("gamerTag", gamerTag)
            sharedPrefEditor?.putString("tag", tag)
            sharedPrefEditor?.apply()
            val action =
                YourStatsFragmentDirections.actionYourStatsFragmentToYourStatsPreviewFragment(
                    gamerTag,
                    tag
                )
            findNavController().navigate(action)
        }

        return binding.root
    }

}