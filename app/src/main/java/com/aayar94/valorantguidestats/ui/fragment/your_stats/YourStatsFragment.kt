package com.aayar94.valorantguidestats.ui.fragment.your_stats

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.databinding.FragmentYourStatsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourStatsFragment : Fragment() {
    private var _binding: FragmentYourStatsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: YourStatsFragmentViewModel by viewModels()
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
        spinnerSetup()
        binding.checkServerStatusButton.setOnClickListener {
            checkServerStatus()
        }

        return binding.root
    }

    private fun checkServerStatus() {
        val selectedServer = binding.serverListSpinner.selectedItem.toString()
        viewModel.getServerStatus(selectedServer)
        viewModel.serverStatus.observe(viewLifecycleOwner) {
            val alertDialogBuilder = MaterialAlertDialogBuilder(binding.root.context)
            alertDialogBuilder.setTitle("$selectedServer ${getString(R.string.server_status)}")
            val icon = if (it.data?.data?.maintenances?.isNotEmpty() == true) {
                R.drawable.ic_server_connection_failiture
            } else {
                R.drawable.ic_server_connection_success
            }
            alertDialogBuilder.setIcon(icon)
            val message = if (it.data?.data?.maintenances?.isNotEmpty() == true) {
                getString(R.string.there_is_a_maintenance_but_game_can_be_playable)
            } else {
                getString(R.string.there_is_no_issue)
            }
            alertDialogBuilder.setMessage(message)
            alertDialogBuilder.setPositiveButton(
                binding.root.context.getString(R.string.okay),
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
            alertDialogBuilder.show()
        }
    }

    private fun spinnerSetup() {
        val spinnerItemList = listOf<String>("eu", "ap", "na", "kr", "latam", "br")
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItemList
        )
        binding.serverListSpinner.adapter = spinnerAdapter
    }

}