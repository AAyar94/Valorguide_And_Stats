package com.aayar94.valorantguidestats.ui.fragment.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.databinding.RowLayoutTiersBinding

class StatsAdapter() : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    private val tiersList: MutableList<Tiers> = mutableListOf()

    inner class StatsViewHolder(private val binding: RowLayoutTiersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            //binding.root.background.colorFilter(tiersList[position].backgroundColor)
            binding.tierImage.load(tiersList[position].largeIcon)
            binding.tierName.text = tiersList[position].tierName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val binding =
            RowLayoutTiersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tiersList.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        return holder.bind(position)
    }

    fun setData(list: Array<Tiers>?) {
        tiersList.clear()
        if (list != null) {
            for (element in list)
                tiersList.add(element)
            this.notifyDataSetChanged()
        }
    }

}