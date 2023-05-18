package com.aayar94.valorantguidestats.ui.fragment.seasons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.game_content.Season
import com.aayar94.valorantguidestats.databinding.RowLayoutSeasonListBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SeasonsAdapter : ListAdapter<Season, SeasonsAdapter.SeasonViewHolder>(
    SeasonsDiffUtil()
) {

    inner class SeasonViewHolder(private val binding: RowLayoutSeasonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                val season = currentList[position]
                with(season) {
                    seasonsNameText.text = displayName/*seasonStartDate.text =
                        seasonStartDate.context.getString(R.string.start_date) + timeFormatter(
                            seasonList[position].startTime.toString()
                        )
                    seasonEndDate.text =
                        seasonEndDate.context.getString(R.string.end_date) + timeFormatter(seasonList[position].endTime.toString())*/

                    seasonStartDate.text =
                        seasonStartDate.context.getString(R.string.start_date) + formatDate(
                            startTime
                        )
                    seasonEndDate.text =
                        seasonEndDate.context.getString(R.string.end_date) + formatDate(endTime)
                    root.animation = AnimationUtils.loadAnimation(
                        root.context, R.anim.recycler_view_item_falldown_anim
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding = RowLayoutSeasonListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SeasonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("WeekBasedYear")
    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("EEE dd-MM-YYYY", Locale.getDefault())
        return dateFormat.format(date)
    }

}

class SeasonsDiffUtil : DiffUtil.ItemCallback<Season>() {
    override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
        return oldItem == newItem
    }
}
