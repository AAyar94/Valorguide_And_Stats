package com.aayar94.valorantguidestats.ui.fragment.maps

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.databinding.RowLayoutMapsBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MapsAdapter(val onItemClick: (map: ValorantMap, extra: FragmentNavigator.Extras) -> Unit) :
    RecyclerView.Adapter<MapsAdapter.MapsViewHolder>() {

    private val mapList: MutableList<ValorantMap> =
        mutableListOf()

    inner class MapsViewHolder(private val binding: RowLayoutMapsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            Glide.with(binding.root.context)
                .asBitmap()
                .load(mapList[position].listViewIcon)
                .into(binding.mapImage)
            binding.mapName.text = mapList[position].displayName

            binding.root.setOnClickListener {
                if (mapList[position].uuid == "ee613ee9-28b7-4beb-9666-08db13bb2244") {
                    val alertDialogBuilder = MaterialAlertDialogBuilder(binding.root.context)
                    alertDialogBuilder.setTitle(mapList[position].displayName)
                    alertDialogBuilder.setIcon(R.drawable.ic_maps)
                    alertDialogBuilder.setMessage(binding.root.context.getString(R.string.this_map_is_a_game_training_area_unfortunately_doesn_t_have_a_detailed_map_view))
                    alertDialogBuilder.setPositiveButton(
                        binding.root.context.getString(R.string.okay),
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })
                    alertDialogBuilder.show()
                } else {
                    it.transitionName = "mapImageT"
                    val extra = FragmentNavigatorExtras(
                        binding.mapImage to "mapImageT"
                    )
                    onItemClick(mapList[position], extra)
                }
            }

            binding.root.animation = AnimationUtils.loadAnimation(
                binding.root.context,
                R.anim.recycler_view_item_falldown_anim
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder {
        val binding =
            RowLayoutMapsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mapList.size
    }

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<ValorantMap>?) {
        if (list != null) {
            mapList.clear()
            mapList.addAll(list)
        }
    }

}