package com.aayar94.valorantguidestats.ui.fragment.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.databinding.RowLayoutWeaponsBinding

class WeaponsAdapter : RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder>() {

    private var weaponList: MutableList<Weapon> = mutableListOf()

    inner class WeaponsViewHolder(private var binding: RowLayoutWeaponsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                try {
                    carouselImageView.load(weaponList[position].displayIcon) {
                        crossfade(true)
                        placeholder(R.drawable.ic_downloading_placeholder)
                    }
                    tvWeaponname.text = weaponList[position].displayName
                } catch (e: Exception) {
                    Log.e(
                        "weaponarrayerror", e.message.toString() + weaponList[position].displayName
                    )
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsViewHolder {
        val binding =
            RowLayoutWeaponsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weaponList.size
    }

    override fun onBindViewHolder(holder: WeaponsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Weapon>?) {
        if (list != null) {
            for (element in list) {
                weaponList.add(element)
            }
        }
    }

}