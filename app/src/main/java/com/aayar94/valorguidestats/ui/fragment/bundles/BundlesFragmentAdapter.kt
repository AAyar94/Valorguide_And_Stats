package com.aayar94.valorguidestats.ui.fragment.bundles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorguidestats.data.models.game_content.Bundles
import com.aayar94.valorguidestats.util.GlideImageLoader
import com.aayar94.valorguidestats.databinding.RowLayoutBundlesBinding

class BundlesFragmentAdapter() : ListAdapter<Bundles, BundlesFragmentAdapter.BundlesViewHolder>(
    BundlesDiffUtil()
) {

    inner class BundlesViewHolder(private val binding: RowLayoutBundlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val bundleItem = currentList[position]
            with(binding) {
                GlideImageLoader(binding.root.context, bundleItem.displayIcon, weaponBundleImage)
                weaponBundleNameText.text = bundleItem.displayName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BundlesViewHolder {
        val binding =
            RowLayoutBundlesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BundlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BundlesViewHolder, position: Int) {
        holder.bind(position)
    }

}

class BundlesDiffUtil : DiffUtil.ItemCallback<Bundles>() {
    override fun areItemsTheSame(oldItem: Bundles, newItem: Bundles): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Bundles, newItem: Bundles): Boolean {
        return oldItem == newItem
    }

}
