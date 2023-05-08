package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Blue
import com.aayar94.valorantguidestats.databinding.RowLayoutMatchPlayersBinding
import com.bumptech.glide.Glide

class TeamBlueRVAdapter : RecyclerView.Adapter<TeamBlueRVAdapter.TeamBlueViewHolder>() {

    val teamBluePlayerList: MutableList<Blue> = mutableListOf()

    inner class TeamBlueViewHolder(val binding: RowLayoutMatchPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.userCardBackground.apply {
                Glide.with(this.context)
                    .load(teamBluePlayerList[position].assets.card.wide)
                    .into(this)
            }
            binding

            binding.userAgent.apply {
                Glide.with(this.context)
                    .load(teamBluePlayerList[position].assets.agent.small)
                    .into(this)
            }
            binding.userNameText.text = teamBluePlayerList[position].name
            val kills=teamBluePlayerList[position].stats.kills.toString()
            val assists=teamBluePlayerList[position].stats.assists.toString()
            val dead=teamBluePlayerList[position].stats.deaths.toString()
            binding.userStatFeedText.text="$kills / $dead / $assists"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBlueViewHolder {
        val binding =
            RowLayoutMatchPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamBlueViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamBluePlayerList.size
    }

    override fun onBindViewHolder(holder: TeamBlueViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: List<Blue>) {
        teamBluePlayerList.clear()
        teamBluePlayerList.addAll(list)
        notifyDataSetChanged()
    }

}