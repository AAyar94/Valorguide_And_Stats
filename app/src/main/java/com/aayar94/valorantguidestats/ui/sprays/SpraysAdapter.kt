package com.aayar94.valorantguidestats.ui.sprays

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.game_content.Spray
import com.aayar94.valorantguidestats.databinding.RowLayoutSpraysBinding
import com.bumptech.glide.Glide

class SpraysAdapter() :
    ListAdapter<Spray, SpraysAdapter.SpraysViewHolder>(
        SprayDiffUtil()
    ) {
    inner class SpraysViewHolder(private val binding: RowLayoutSpraysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val spray = currentList[position]
            with(binding) {
                Glide.with(root).load(spray.displayIcon).into(sprayImage)
                sprayName.text = spray.displayName
            }
        }
    }

    class SprayDiffUtil : DiffUtil.ItemCallback<Spray>() {
        override fun areItemsTheSame(oldItem: Spray, newItem: Spray): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Spray, newItem: Spray): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpraysViewHolder {
        val binding =
            RowLayoutSpraysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpraysViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: SpraysViewHolder, position: Int) {
        holder.bind(position)
    }
}