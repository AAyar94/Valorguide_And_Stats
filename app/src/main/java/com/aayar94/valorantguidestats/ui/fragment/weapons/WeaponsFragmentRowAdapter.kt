package com.aayar94.valorantguidestats.ui.fragment.weapons

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.databinding.RowLayoutWeaponsListBinding


class WeaponsFragmentRowAdapter(val onItemClick: (weapon: Weapon) -> Unit) :
    RecyclerView.Adapter<WeaponsFragmentRowAdapter.WeaponListRowViewHolder>() {

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
                if (weaponList[position].uuid == "2f59173c-4bed-b6c3-2191-dea9b58be9c7") {
                    binding.root.setOnClickListener {
                        Toast.makeText(
                            binding.root.context,
                            binding.root.context.getString(R.string.knife_have_not_details_screen_yet),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    binding.root.setOnClickListener {
                        onItemClick(weaponList[position])
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