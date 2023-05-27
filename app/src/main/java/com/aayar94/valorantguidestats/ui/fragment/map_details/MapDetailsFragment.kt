package com.aayar94.valorantguidestats.ui.fragment.map_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.game_content.Callout
import com.aayar94.valorantguidestats.databinding.FragmentMapDetailsBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapDetailsFragment : Fragment() {
    private var _binding: FragmentMapDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MapDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapDetailsBinding.inflate(layoutInflater, container, false)
        with(binding) {
            with(args.ValorantMap) {
                Glide.with(root.context)
                    .asBitmap()
                    .load(listViewIcon)
                    .into(imgMap)
                startPostponedEnterTransition()
                mapName.text = displayName
                mapCoordinatesText.text =
                    getString(R.string.cordinates) + coordinates
                GlideImageLoader(requireContext(), displayIcon, cordinateView)
            }

            canvasView.layoutParams.width = args.ValorantMap.xMultiplier.toInt()
            canvasView.layoutParams.height = args.ValorantMap.yMultiplier.toInt()
            val mCanvasView = canvasView
            val calloutsList = mutableListOf<Callout>()
            for (i in 0 until args.ValorantMap.callouts.size) {
                calloutsList.add(
                    Callout(
                        args.ValorantMap.callouts[i].regionName,
                        args.ValorantMap.callouts[i].superRegionName,
                        args.ValorantMap.callouts[i].location
                    )
                )
            }
            mCanvasView.setCalloutsAndParams(
                calloutsList,
                args.ValorantMap.xMultiplier,
                args.ValorantMap.yMultiplier,
                args.ValorantMap.xScalarToAdd,
                args.ValorantMap.yScalarToAdd
            )
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}