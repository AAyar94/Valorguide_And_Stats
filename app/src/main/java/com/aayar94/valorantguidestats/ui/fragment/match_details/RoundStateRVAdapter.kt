package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Round
import com.aayar94.valorantguidestats.databinding.RowLayoutRoundWinBinding
import com.bumptech.glide.Glide

class RoundStateRVAdapter : RecyclerView.Adapter<RoundStateRVAdapter.RoundStateViewHolder>() {

    val roundList: MutableList<Round> = mutableListOf()

    inner class RoundStateViewHolder(val binding: RowLayoutRoundWinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.numberOfRoundText.text = (position + 1).toString()
            when (roundList[position].winning_team) {
                "Blue" -> Glide.with(binding.root.context)
                    .load(R.drawable.row_item_blue_win)
                    .into(binding.teamWinState)

                "Red" -> Glide.with(binding.root.context)
                    .load(R.drawable.row_item_red_win)
                    .into(binding.teamWinState)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundStateViewHolder {
        val binding =
            RowLayoutRoundWinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoundStateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return roundList.size
    }

    override fun onBindViewHolder(holder: RoundStateViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: List<Round>) {
        roundList.clear()
        roundList.addAll(list)
        notifyDataSetChanged()
    }


}