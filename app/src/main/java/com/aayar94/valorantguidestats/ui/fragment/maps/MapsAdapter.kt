package com.aayar94.valorantguidestats.ui.fragment.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.databinding.RowLayoutMapsBinding


class MapsAdapter(val onItemClick: (map: ValorantMap) -> Unit) :
    RecyclerView.Adapter<MapsAdapter.MapsViewHolder>() {

    private val mapList: MutableList<ValorantMap> =
        mutableListOf()

    inner class MapsViewHolder(private val binding: RowLayoutMapsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.mapImage.load(mapList[position].listViewIcon) {
                placeholder(R.drawable.ic_downloading_placeholder)
                crossfade(true)
            }
            binding.mapName.text = mapList[position].displayName
            binding.root.setOnClickListener {
                onItemClick(mapList[position])
            }
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