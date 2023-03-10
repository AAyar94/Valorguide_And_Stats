package com.aayar94.valorantguidestats.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.databinding.RowLayoutGamemodesBinding

class GameModesAdapter(val onItemClick: (gamemode: Gamemode) -> Unit) :
    RecyclerView.Adapter<GameModesAdapter.GameModesViewHolder>() {

    private val _gamemodeList: MutableList<Gamemode> = mutableListOf()

    inner class GameModesViewHolder(private val binding: RowLayoutGamemodesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                carouselImageView.load(_gamemodeList[position].displayIcon) {
                    crossfade(true)
                    placeholder(R.drawable.ic_downloading_placeholder)
                }
                tvGamemodeName.text = _gamemodeList[position].displayName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameModesViewHolder {
        val binding =
            RowLayoutGamemodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameModesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _gamemodeList.size
    }

    override fun onBindViewHolder(holder: GameModesViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Gamemode>?) {
        for (i in 0 until list!!.size) {
            _gamemodeList.add(list[i])
        }
        this.notifyDataSetChanged()
    }

}