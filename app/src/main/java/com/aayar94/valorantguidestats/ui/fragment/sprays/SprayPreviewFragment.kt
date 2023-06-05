package com.aayar94.valorantguidestats.ui.fragment.sprays

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.aayar94.valorantguidestats.databinding.FragmentSprayPreviewBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import java.io.File
import java.io.FileOutputStream

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
        binding.shareStickerButton.setOnClickListener {
            shareImage()

        }
        return binding.root
    }

    private fun shareImage() {
        val bitmap = binding.peekView.drawable.toBitmap()

        // Save the image to a temporary file
        val cachePath = File(requireActivity().applicationContext.cacheDir, "images")
        val imagePath = File(cachePath, "image.png")
        val imageUri: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            imagePath
        )
        val outputStream = FileOutputStream(imagePath)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()

        // Create an intent to share the image
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)

        // Start the sharing activity
        startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}