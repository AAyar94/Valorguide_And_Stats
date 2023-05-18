package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Red
import com.aayar94.valorantguidestats.databinding.RowLayoutMatchPlayersBinding
import com.bumptech.glide.Glide

class TeamRedRVAdapter : ListAdapter<Red, TeamRedRVAdapter.TeamRedViewHolder>(
    TeamRedDiffUtil()
) {

    inner class TeamRedViewHolder(val binding: RowLayoutMatchPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val red = currentList[position]
            with(red) {
                binding.userCardBackground.apply {
                    Glide.with(this.context)
                        .load(assets.card.wide)
                        .into(this)
                }
                binding.userAgent.apply {
                    Glide.with(this.context)
                        .load(assets.agent.small)
                        .into(this)
                }
                binding.userNameText.text = name
                val kills = stats.kills.toString()
                val assists = stats.assists.toString()
                val dead = stats.deaths.toString()
                binding.userStatFeedText.text = "$kills / $dead / $assists"
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRedViewHolder {
        val binding =
            RowLayoutMatchPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamRedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TeamRedViewHolder, position: Int) {
        holder.bind(position)
    }
}

class TeamRedDiffUtil : DiffUtil.ItemCallback<Red>() {
    override fun areItemsTheSame(oldItem: Red, newItem: Red): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Red, newItem: Red): Boolean {
        return oldItem == newItem
    }
}
