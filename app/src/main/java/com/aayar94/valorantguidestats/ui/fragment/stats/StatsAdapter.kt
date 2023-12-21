package com.aayar94.valorantguidestats.ui.fragment.stats

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.game_content.TierDetail
import com.aayar94.valorantguidestats.util.GlideImageLoader
import com.aayar94.valorguidestats.databinding.RowLayoutTiersBinding


class StatsAdapter : ListAdapter<TierDetail, StatsAdapter.StatsViewHolder>(
    StatsDiffUtil()
) {


    inner class StatsViewHolder(private val binding: RowLayoutTiersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                val tier = currentList[position]
                with(tier) {
                    val colorCard = "#${(backgroundColor.toString())}"
                    tierCard.setCardBackgroundColor(Color.parseColor(colorCard))
                    GlideImageLoader(
                        root.context,
                        tier.smallIcon.toString(),
                        tierImage
                    )
                    binding.tierName.text = tierName
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val binding =
            RowLayoutTiersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        return holder.bind(position)
    }

    class StatsDiffUtil : DiffUtil.ItemCallback<TierDetail>() {
        override fun areItemsTheSame(oldItem: TierDetail, newItem: TierDetail): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TierDetail, newItem: TierDetail): Boolean {
            return oldItem == newItem
        }
    }
}

