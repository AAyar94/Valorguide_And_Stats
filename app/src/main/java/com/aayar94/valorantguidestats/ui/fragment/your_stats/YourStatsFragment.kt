package com.aayar94.valorantguidestats.ui.fragment.your_stats

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYourStatsBinding.inflate(layoutInflater, container, false)

        gamerTag = viewModel.readUserGamerTagEntry(requireContext())
        tag = viewModel.readUserTagEntrty(requireContext())

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
            if (binding.rememberMeSwitch.isChecked) {
                viewModel.saveUserEntries(requireContext(), gamerTag, tag)
            }
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
        viewModel.serverStatus.observe(viewLifecycleOwner) {
            viewModel.getServerStatus(selectedServer)
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
                binding.root.context.getString(R.string.okay)
            ) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialogBuilder.show()
        }
        viewModel.serverStatus.removeObservers(viewLifecycleOwner)
    }

    private fun spinnerSetup() {
        val spinnerItemList = listOf("eu", "ap", "na", "kr", "latam", "br")
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItemList
        )
        binding.serverListSpinner.adapter = spinnerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}