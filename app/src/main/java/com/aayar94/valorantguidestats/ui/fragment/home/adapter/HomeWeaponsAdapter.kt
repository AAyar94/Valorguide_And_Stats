package com.aayar94.valorantguidestats.ui.fragment.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.databinding.RowLayoutWeaponsHomeBinding

class HomeWeaponsAdapter : RecyclerView.Adapter<HomeWeaponsAdapter.WeaponsViewHolder>() {

    private var homeWeaponList: MutableList<Weapon> = mutableListOf()

    inner class WeaponsViewHolder(private var binding: RowLayoutWeaponsHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                try {
                    carouselImageView.load(homeWeaponList[position].displayIcon) {
                        crossfade(true)
                        placeholder(R.drawable.ic_downloading_placeholder)
                    }
                    tvWeaponname.text = homeWeaponList[position].displayName
                } catch (e: Exception) {
                    Log.e(
                        "weaponarrayerror", e.message.toString() + homeWeaponList[position].displayName
                    )
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsViewHolder {
        val binding =
            RowLayoutWeaponsHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeWeaponList.size
    }

    override fun onBindViewHolder(holder: WeaponsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Weapon>?) {
        if (list != null) {
            for (element in list) {
                homeWeaponList.add(element)
            }
        }
        notifyDataSetChanged()
    }

}