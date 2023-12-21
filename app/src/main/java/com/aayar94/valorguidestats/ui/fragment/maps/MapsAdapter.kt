package com.aayar94.valorguidestats.ui.fragment.maps

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorguidestats.data.models.game_content.ValorantMap
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.RowLayoutMapsBinding
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MapsAdapter(
    val onItemClick: (map: ValorantMap, extra: FragmentNavigator.Extras) -> Unit
) : ListAdapter<ValorantMap, MapsAdapter.MapsViewHolder>(MapsDiffUtil()) {

    inner class MapsViewHolder(private val binding: RowLayoutMapsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                val map = currentList[position]
                with(map) {
                    Glide.with(root.context)
                        .asBitmap()
                        .load(listViewIcon)
                        .into(mapImage)
                    mapName.text = displayName

                    root.setOnClickListener {
                        if (uuid == "ee613ee9-28b7-4beb-9666-08db13bb2244") {
                            val alertDialogBuilder = MaterialAlertDialogBuilder(root.context)
                            with(alertDialogBuilder) {
                                setTitle(displayName)
                                setIcon(R.drawable.ic_maps)
                                setMessage(binding.root.context.getString(R.string.this_map_is_a_game_training_area_unfortunately_doesn_t_have_a_detailed_map_view))
                                setPositiveButton(
                                    binding.root.context.getString(R.string.okay),
                                    DialogInterface.OnClickListener { dialog, _ ->
                                        dialog.dismiss()
                                    })
                                show()
                            }
                        } else {
                            it.transitionName = "mapImageT"
                            val extra = FragmentNavigatorExtras(
                                mapImage to "mapImageT"
                            )
                            onItemClick(this, extra)
                        }
                    }

                    root.animation = AnimationUtils.loadAnimation(
                        root.context,
                        R.anim.recycler_view_item_falldown_anim
                    )
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder {
        val binding =
            RowLayoutMapsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.bind(position)
    }
}

class MapsDiffUtil : DiffUtil.ItemCallback<ValorantMap>() {
    override fun areItemsTheSame(oldItem: ValorantMap, newItem: ValorantMap): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ValorantMap, newItem: ValorantMap): Boolean {
        return oldItem == newItem
    }
}
