package com.aayar94.valorantguidestats.ui.fragment.weapons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.game_content.Weapon
import com.aayar94.valorantguidestats.util.GlideImageLoader
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.RowLayoutWeaponsListBinding


class WeaponsFragmentRowAdapter(val onItemClick: (weapon: Weapon) -> Unit) :
    ListAdapter<Weapon, WeaponsFragmentRowAdapter.WeaponListRowViewHolder>(
        WeaponDiffUtil()
    ) {

    inner class WeaponListRowViewHolder(val binding: RowLayoutWeaponsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                val weaponList = currentList[position]
                with(weaponList) {
                    weaponName.text = displayName
                    GlideImageLoader(
                        root.context,
                        displayIcon,
                        weaponImage
                    )

                    root.animation = AnimationUtils.loadAnimation(
                        root.context,
                        R.anim.recycler_view_item_falldown_anim
                    )

                    root.setOnClickListener {
                        onItemClick(this)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponsFragmentRowAdapter.WeaponListRowViewHolder {
        val binding =
            RowLayoutWeaponsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponListRowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: WeaponListRowViewHolder, position: Int) {
        holder.bind(position)
    }
}

class WeaponDiffUtil : DiffUtil.ItemCallback<Weapon>() {
    override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return oldItem == newItem
    }
}
