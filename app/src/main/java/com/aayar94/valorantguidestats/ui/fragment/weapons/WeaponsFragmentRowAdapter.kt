package com.aayar94.valorantguidestats.ui.fragment.weapons

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.databinding.RowLayoutWeaponsListBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader


class WeaponsFragmentRowAdapter(val onItemClick: (weapon: Weapon) -> Unit) :
    RecyclerView.Adapter<WeaponsFragmentRowAdapter.WeaponListRowViewHolder>() {

    private var weaponList: MutableList<Weapon> = mutableListOf()

    inner class WeaponListRowViewHolder(val binding: RowLayoutWeaponsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                weaponName.text = weaponList[position].displayName
                GlideImageLoader(
                    binding.root.context,
                    weaponList[position].displayIcon,
                    weaponImage
                )

                binding.root.animation = AnimationUtils.loadAnimation(
                    binding.root.context,
                    R.anim.recycler_view_item_falldown_anim
                )

                binding.root.setOnClickListener {
                    onItemClick(weaponList[position])
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
        return weaponList.size
    }

    override fun onBindViewHolder(holder: WeaponListRowViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Weapon>?) {
        weaponList.clear()
        weaponList.addAll(list!!)
        this.notifyDataSetChanged()
    }

}