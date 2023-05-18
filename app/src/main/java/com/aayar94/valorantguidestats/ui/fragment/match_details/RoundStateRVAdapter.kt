package com.aayar94.valorantguidestats.ui.fragment.match_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.Round
import com.aayar94.valorantguidestats.databinding.RowLayoutRoundWinBinding
import com.bumptech.glide.Glide

class RoundStateRVAdapter : ListAdapter<Round, RoundStateRVAdapter.RoundStateViewHolder>(
    RoundStateDiffUtil()
) {

    inner class RoundStateViewHolder(val binding: RowLayoutRoundWinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                val round = currentList[position]
                with(round) {
                    numberOfRoundText.text = (position + 1).toString()
                    when (winning_team) {
                        "Blue" -> Glide.with(root.context)
                            .load(R.drawable.row_item_blue_win)
                            .into(teamWinState)

                        "Red" -> Glide.with(root.context)
                            .load(R.drawable.row_item_red_win)
                            .into(teamWinState)

                        else -> {}
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundStateViewHolder {
        val binding =
            RowLayoutRoundWinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoundStateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: RoundStateViewHolder, position: Int) {
        holder.bind(position)
    }
}

class RoundStateDiffUtil : DiffUtil.ItemCallback<Round>() {
    override fun areItemsTheSame(oldItem: Round, newItem: Round): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Round, newItem: Round): Boolean {
        return oldItem == newItem
    }

}
