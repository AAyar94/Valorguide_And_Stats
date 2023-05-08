package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Red
import com.aayar94.valorantguidestats.databinding.RowLayoutMatchPlayersBinding
import com.bumptech.glide.Glide

class TeamRedRVAdapter : RecyclerView.Adapter<TeamRedRVAdapter.TeamRedViewHolder>() {

    val teamRedPlayerList: MutableList<Red> = mutableListOf()

    inner class TeamRedViewHolder(val binding: RowLayoutMatchPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.userCardBackground.apply {
                Glide.with(this.context)
                    .load(teamRedPlayerList[position].assets.card.wide)
                    .into(this)
            }
            binding

            binding.userAgent.apply {
                Glide.with(this.context)
                    .load(teamRedPlayerList[position].assets.agent.small)
                    .into(this)
            }
            binding.userNameText.text = teamRedPlayerList[position].name
            val kills=teamRedPlayerList[position].stats.kills.toString()
            val assists=teamRedPlayerList[position].stats.assists.toString()
            val dead=teamRedPlayerList[position].stats.deaths.toString()
            binding.userStatFeedText.text="$kills / $dead / $assists"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRedViewHolder {
        val binding =
            RowLayoutMatchPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamRedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamRedPlayerList.size
    }

    override fun onBindViewHolder(holder: TeamRedViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: List<Red>) {
        teamRedPlayerList.clear()
        teamRedPlayerList.addAll(list)
        notifyDataSetChanged()
    }

}