package com.aayar94.valorantguidestats.ui.fragment.stats

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.TierDetail
import com.aayar94.valorantguidestats.databinding.RowLayoutTiersBinding


class StatsAdapter() : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    private val tiersList: MutableList<TierDetail> = mutableListOf()

    inner class StatsViewHolder(private val binding: RowLayoutTiersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val colorCard = "#${(tiersList[position].backgroundColor.toString())}"
            binding.tierCard.setCardBackgroundColor(Color.parseColor(colorCard))
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

    fun setData(list: ArrayList<TierDetail>?) {
        tiersList.clear()
        if (list != null) {
            for (i in 0 until list.size) {
                if (list[i].tier !== 1) {
                    if (list[i].tier !== 2) {
                        tiersList.add(list[i])
                    }
                }
            }
        }
        notifyDataSetChanged()
    }
}

