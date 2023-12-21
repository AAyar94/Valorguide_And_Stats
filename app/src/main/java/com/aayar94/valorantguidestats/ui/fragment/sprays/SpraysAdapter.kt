package com.aayar94.valorantguidestats.ui.fragment.sprays

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.game_content.Spray
import com.aayar94.valorguidestats.databinding.RowLayoutSpraysBinding
import com.bumptech.glide.Glide


class SpraysAdapter(private val onLongClicked: (animationLink: String) -> Unit) :
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

                if (!spray.animationGif.isNullOrEmpty()){
                    binding.animatedSticker.visibility= View.VISIBLE
                }

                root.setOnClickListener {
                    if (spray.animationGif.isNullOrEmpty()){
                        onLongClicked(spray.fullTransparentIcon.toString())
                    }else{
                        onLongClicked(spray?.animationGif.toString())
                    }
                }
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