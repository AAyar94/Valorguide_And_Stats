package com.aayar94.valorantguidestats.ui.fragment.map_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentMapDetailsBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapDetailsFragment : Fragment() {
    private var _binding: FragmentMapDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MapDetailsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapDetailsBinding.inflate(layoutInflater, container, false)
        with(binding) {
            with(args.ValorantMap) {
                imgMap.transitionName = "mapImageT"
                Glide.with(root.context)
                    .asBitmap()
                    .load(listViewIcon)
                    .into(imgMap)
                startPostponedEnterTransition()
                mapName.text = displayName
                mapCoordinatesText.text =
                    getString(R.string.cordinates) + coordinates
                GlideImageLoader(requireContext(), displayIcon!!, cordinateView)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}