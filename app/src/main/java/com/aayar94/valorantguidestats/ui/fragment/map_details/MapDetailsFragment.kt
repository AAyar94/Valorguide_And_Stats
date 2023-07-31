package com.aayar94.valorantguidestats.ui.fragment.map_details

import android.annotation.SuppressLint
import android.os.Bundle
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
                //calloutViewSetter(args.ValorantMap.uuid)
                mapName.text = displayName
                if(coordinates.isNullOrBlank()){
                    mapCoordinatesText.text =
                        """${getString(R.string.cordinates)}${getString(R.string.unknown)}"""
                    mapCoordinatesText.visibility=View.GONE
                }else{
                    mapCoordinatesText.text =
                        getString(R.string.cordinates) + coordinates
                    mapCoordinatesText.visibility=View.VISIBLE
                }
                GlideImageLoader(requireContext(), displayIcon, cordinateView)
            }
        }
        return binding.root
    }

    /* private fun calloutViewSetter(uuid: String) {
         when (uuid) {
             "7eaecc1b-4337-bbf6-6ab9-04b8f06b3319" -> calloutImageLoader(R.raw.ascent)
             "d960549e-485c-e861-8d71-aa9d1aed12a2" -> calloutImageLoader(R.raw.split)
             "b529448b-4d60-346e-e89e-00a4c527a405" -> calloutImageLoader(R.raw.fracture)
             "2c9d57ec-4431-9c5e-2939-8f9ef6dd5cba" -> calloutImageLoader(R.raw.bind)
             "2fb9a4fd-47b8-4e7d-a969-74b4046ebd53" -> calloutImageLoader(R.raw.breeze)
             "2fe4ed3a-450a-948b-6d6b-e89a78e680a9" -> calloutImageLoader(R.raw.lotus)
             "fd267378-4d1d-484f-ff52-77821ed10dc2" -> calloutImageLoader(R.raw.pearl)
             "e2ad5c54-4114-a870-9641-8ea21279579a" -> calloutImageLoader(R.raw.icebox)
             "2bee0dc9-4ffe-519b-1cbd-7fbe763a6047" -> calloutImageLoader(R.raw.haven)
         }
     }*/

    fun calloutImageLoader(image: Int) {
        Glide.with(binding.root)
            .load(image)
            .into(binding.calloutView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}