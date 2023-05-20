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
        if (videoLink.isNotBlank()) {
            val mediaController = android.widget.MediaController(context)
            mediaController.setAnchorView(binding.videoView)
            mediaController.setMediaPlayer(binding.videoView)
            binding.videoView.setMediaController(mediaController)

            val uri = Uri.parse(videoLink)
            binding.videoView.setVideoURI(uri)
            binding.videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.start()
            }
        } else {
            binding.videoView.visibility = View.GONE
            binding.cardViewVideolessSkin.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}