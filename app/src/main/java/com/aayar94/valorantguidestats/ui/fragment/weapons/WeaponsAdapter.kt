package com.aayar94.valorantguidestats.ui.fragment.weapons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.databinding.RowLayoutWeaponsListBinding
import com.aayar94.valorantguidestats.ui.fragment.home.adapter.WeaponsAdapter
import com.aayar94.valorantguidestats.ui.fragment.weapons.WeaponsFragmentRowAdapter.WeaponListRowViewHolder as WeaponsAdapter.WeaponsViewHolder

class WeaponsFragmentRowAdapter(onItemClick: (weapon: Weapon) -> Unit) :
    RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder>() {

    private var weaponList: MutableList<Weapon> = mutableListOf()

    inner class WeaponListRowViewHolder(val binding: RowLayoutWeaponsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                weaponName.text = weaponList[position].displayName
                weaponImage.load(weaponList[position].displayIcon) {
                    crossfade(true)
                    placeholder(R.drawable.ic_downloading_placeholder)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponsAdapter.WeaponsViewHolder {
        val binding =
            RowLayoutWeaponsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponsAdapter.WeaponsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weaponList.size
    }

    override fun onBindViewHolder(holder: WeaponsAdapter.WeaponsViewHolder, position: Int) {
        holder.bind(position)
    }


}