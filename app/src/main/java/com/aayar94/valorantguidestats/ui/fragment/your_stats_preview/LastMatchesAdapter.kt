package com.aayar94.valorantguidestats.ui.fragment.your_stats_preview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorantguidestats.databinding.RowLayoutUserMatchesBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LastMatchesAdapter(val onClick: (userMatchId: String) -> Unit) :
    RecyclerView.Adapter<LastMatchesAdapter.MatchesViewHolder>() {

    private var matchList: MutableList<UserMatchesDataModel> = mutableListOf()

    inner class MatchesViewHolder(private val binding: RowLayoutUserMatchesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.mapNameText.text = matchList[0].data[position].meta.map.name
            binding.matchTypeText.text = matchList[0].data[position].meta.mode
            binding.matchDateText.text = newFormatDate(matchList[0].data[position].meta.started_at)
            binding.root.setOnClickListener {
                onClick(matchList[0].data[position].meta.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val binding =
            RowLayoutUserMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matchList[0].data.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: UserMatchesDataModel) {
        matchList.clear()
        matchList.add(list)
        this.notifyDataSetChanged()
    }

    fun newFormatDate(startDate: String): String? {
        val timestamp = Instant.parse(startDate)
        val userTimeZone = ZoneId.systemDefault()
        val userDateTime = LocalDateTime.ofInstant(timestamp, userTimeZone)
        return userDateTime.format(DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy"))
    }

}

