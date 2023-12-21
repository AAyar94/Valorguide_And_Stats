package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Blue
import com.aayar94.valorantguidestats.databinding.RowLayoutMatchPlayersBinding
import com.bumptech.glide.Glide

class TeamBlueRVAdapter : ListAdapter<Blue, TeamBlueRVAdapter.TeamBlueViewHolder>(
    TeamBlueDiffUtil()
) {

    inner class TeamBlueViewHolder(val binding: RowLayoutMatchPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(position: Int) {
            with(binding) {
                val blue = currentList[position]
                with(blue) {
                    userCardBackground.apply {
                        Glide.with(this.context)
                            .load(assets.card.wide)
                            .into(this)
                    }
                    Glide.with(userAgent.context)
                        .load(assets.agent.small)
                        .into(userAgent)
                    userNameText.text = name
                    userStatFeedText.setTextColor(root.context.getColor(R.color.BlueLight))
                    userNameText.setTextColor(root.context.getColor(R.color.BlueLight))
                    kdaText.setTextColor(root.context.getColor(R.color.BlueLight))
                    val kills = stats.kills.toString()
                    val assists = stats.assists.toString()
                    val dead = stats.deaths.toString()
                    userStatFeedText.text = "$kills / $dead / $assists"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBlueViewHolder {
        val binding =
            RowLayoutMatchPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamBlueViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: TeamBlueViewHolder, position: Int) {
        holder.bind(position)
    }
}

class TeamBlueDiffUtil : DiffUtil.ItemCallback<Blue>() {
    override fun areItemsTheSame(oldItem: Blue, newItem: Blue): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Blue, newItem: Blue): Boolean {
        return oldItem == newItem
    }
}
