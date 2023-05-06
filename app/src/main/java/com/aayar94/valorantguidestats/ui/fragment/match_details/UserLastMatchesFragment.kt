package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.databinding.FragmentUserLastMatchesBinding


class UserLastMatchesFragment : Fragment() {
    private var _binding: FragmentUserLastMatchesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MatchDetailsViewModel by viewModels()
    private val args: UserLastMatchesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLastMatchesBinding.inflate(inflater, container, false)


        viewModel.getMatchDetails(args.matchID)

        viewModel.matchDetails.observe(viewLifecycleOwner){
            with(binding){

            }
        }


        return binding.root
    }

}