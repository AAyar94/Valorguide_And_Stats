package com.aayar94.valorantguidestats.ui.fragment.seasons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.databinding.RowLayoutSeasonListBinding

class SeasonsAdapter() : RecyclerView.Adapter<SeasonsAdapter.SeasonViewHolder>() {

    private val seasonList: MutableList<Season> = mutableListOf()

    inner class SeasonViewHolder(private val binding: RowLayoutSeasonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                seasonsNameText.text = seasonList[position].displayName
                seasonStartDate.text =
                    seasonStartDate.context.getString(R.string.start_date) + timeFormatter(seasonList[position].startTime.toString())
                seasonEndDate.text = seasonEndDate.context.getString(R.string.end_date) + timeFormatter(seasonList[position].endTime.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding =
            RowLayoutSeasonListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SeasonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Season>?) {
        seasonList.clear()
        if (list != null) {
            for (i in 0 until list.size) {
                seasonList.add(list[i])
            }
        }
        this.notifyDataSetChanged()
    }

    fun timeFormatter(string: String): String {
        val time = string.substring(0, 10)
        val year = string.substring(30, 34)
        return "$time $year"
    }

}
