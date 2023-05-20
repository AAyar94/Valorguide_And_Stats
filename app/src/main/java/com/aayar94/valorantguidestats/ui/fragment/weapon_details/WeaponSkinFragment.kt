package com.aayar94.valorantguidestats.ui.fragment.weapon_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.valorantguidestats.databinding.FragmentWeaponSkinBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader

class WeaponSkinFragment(private val imageLink: String, private val videoLink: String) :
    Fragment() {
    private var _binding: FragmentWeaponSkinBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponSkinBinding.inflate(layoutInflater, container, false)

        GlideImageLoader(requireContext(), imageLink, binding.weaponSkinImage)

        if (videoLink.isNullOrBlank()) {
            binding.videoView.visibility = View.GONE
            binding.cardViewVideolessSkin.visibility = View.VISIBLE
        } else {
            binding.videoView.visibility = View.VISIBLE
            binding.cardViewVideolessSkin.visibility = View.GONE
            // Uri object to refer the
            // resource from the videoUrl
            val uri = Uri.parse(videoLink)

            // sets the resource from the
            // videoUrl to the videoView
            binding.videoView.setVideoURI(uri)

            // creating object of
            // media controller class
            val mediaController = android.widget.MediaController(requireContext())

            // sets the anchor view
            // anchor view for the videoView
            mediaController.setAnchorView(binding.videoView)

            // sets the media player to the videoView
            mediaController.setMediaPlayer(binding.videoView)

            // sets the media controller to the videoView
            binding.videoView.setMediaController(mediaController);

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}