package com.aayar94.valorguidestats.ui.fragment.weapon_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.valorguidestats.util.GlideImageLoader
import com.aayar94.valorguidestats.databinding.FragmentWeaponSkinBinding
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class WeaponSkinFragment(private val imageLink: String, private val videoLink: String) :
    Fragment() {
    private var _binding: FragmentWeaponSkinBinding? = null
    private val binding get() = _binding!!
    private var player: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponSkinBinding.inflate(layoutInflater, container, false)

        GlideImageLoader(requireContext(), imageLink, binding.weaponSkinImage)
        if (videoLink.isNotBlank()) {
            initializePlayer()
            with(binding) {
                videoPreviewHeader.visibility = View.VISIBLE
                videoPlayer.visibility = View.VISIBLE
                videoPreviewDivider.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                videoPreviewHeader.visibility = View.GONE
                videoPlayer.visibility = View.GONE
                videoPreviewDivider.visibility = View.GONE
            }
        }

        return binding.root
    }

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector(requireContext())
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(1500, 5000, 500, 1400)
            .createDefaultLoadControl()
        val renderersFactory = DefaultRenderersFactory(requireContext())

        player = SimpleExoPlayer.Builder(requireContext(), renderersFactory)
            .setTrackSelector(trackSelector)
            .setLoadControl(loadControl)
            .build()
        binding.videoPlayer.player = player

        //val userAgent = Util.getUserAgent(requireContext(), getString(R.string.app_name))
        val mediaUri = Uri.parse(videoLink)

        val dataSourceFactory = DefaultDataSourceFactory(requireContext())
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaUri)

        player?.prepare(mediaSource)
        player?.playWhenReady = false
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}