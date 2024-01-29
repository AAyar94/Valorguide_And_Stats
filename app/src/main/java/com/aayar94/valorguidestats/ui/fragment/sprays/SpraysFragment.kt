package com.aayar94.valorguidestats.ui.fragment.sprays

import android.annotation.SuppressLint
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
import androidx.fragment.app.viewModels
import com.aayar94.valorguidestats.core.util.GlideImageLoader
import com.aayar94.valorguidestats.databinding.DialogSpreyPreviewBinding
import com.aayar94.valorguidestats.databinding.FragmentSpraysBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@SuppressLint("InflateParams")
@AndroidEntryPoint
class SpraysFragment : Fragment() {
    private var mBinding: FragmentSpraysBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: SprayViewModel by viewModels()
    private val adapter: SpraysAdapter by lazy {
        SpraysAdapter { sprayLink ->
            val builder = MaterialAlertDialogBuilder(requireContext())
            val view = DialogSpreyPreviewBinding.inflate(layoutInflater)
            view.shareStickerButton.setOnClickListener {
                val bitmap = view.peekView.drawable.toBitmap()

                val cachePath = File(requireContext().cacheDir, "images")
                cachePath.mkdirs()

                val imagePath = File(cachePath, "image.png")

                val imageUri: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "${requireContext().packageName}.fileprovider",
                    imagePath
                )

                val outputStream = FileOutputStream(imagePath)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/*"
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)

                startActivity(Intent.createChooser(shareIntent, "Share Image"))
            }
            GlideImageLoader(requireContext(), sprayLink, view.peekView)
            builder.setView(view.root)
            builder.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSpraysBinding.inflate(layoutInflater, container, false)

        binding.spraysRecyclerView.adapter = adapter

        viewModel.sprays.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSprays()
    }
}