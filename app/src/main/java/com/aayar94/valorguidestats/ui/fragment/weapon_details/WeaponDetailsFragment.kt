package com.aayar94.valorguidestats.ui.fragment.weapon_details

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aayar94.valorguidestats.core.util.GlideImageLoader
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.FragmentWeaponDetailsBinding


class WeaponDetailsFragment : Fragment() {
    private var _binding: FragmentWeaponDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: WeaponDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponDetailsBinding.inflate(layoutInflater, container, false)

        screenSetup()
        skinScreenSetup()

        return binding.root
    }

    private fun skinScreenSetup() {
        val title = ArrayList<String>()
        for (index in 0 until args.weapon.skins.size) {
            title.add(args.weapon.skins[index].displayName)
        }
        val fragmentList = ArrayList<Fragment>()
        for (index2 in 0 until args.weapon.skins.size) {
            fragmentList.add(
                WeaponSkinFragment(
                    args.weapon.skins[index2].chromas[0].fullRender,
                    args.weapon.skins[index2].levels.last().streamedVideo ?: ""
                )
            )
        }
        val adapter = WeaponSkinViewPagerAdapter(fragmentList, title, childFragmentManager)
        binding.skinViewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.skinViewPager)

    }

    private fun screenSetup() {
        with(binding) {
            GlideImageLoader(requireContext(), args.weapon.displayIcon, weaponDetailImage)
            weaponDetailName.text = args.weapon.displayName
            weaponClassText.text = args.weapon.shopData?.categoryText ?: getString(R.string.knife)
            headDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.headDamage.toString()
            bodyDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.bodyDamage.toString()
            legDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.legDamage.toString()
        }

        if (args.weapon.uuid != "2f59173c-4bed-b6c3-2191-dea9b58be9c7") {
            with(binding.progressBarHeadDamage) {
                val headProgress =
                    args.weapon.weaponStats?.damageRanges?.get(0)?.headDamage?.toInt()!!
                progress = headProgress
                val animation = ObjectAnimator.ofInt(this, "progress", 0, headProgress)
                animation.duration = 1000
                animation.start()
                isClickable = false
            }
            with(binding.progressBarBodyDamage) {
                val bodyProgress = args.weapon.weaponStats!!.damageRanges[0]?.bodyDamage!!.toInt()
                progress = bodyProgress
                val animation = ObjectAnimator.ofInt(this, "progress", 0, bodyProgress)
                animation.duration = 1000
                animation.start()
                isClickable = false
            }
            with(binding.progressBarLegDamage) {
                val legProgress = args.weapon.weaponStats?.damageRanges?.get(0)?.legDamage!!.toInt()
                progress = legProgress
                val animation = ObjectAnimator.ofInt(this, "progress", 0, legProgress)
                animation.duration = 1000
                animation.start()
                isClickable = false
            }
        } else {
            with(binding) {
                progressBarBodyDamage.progress = 0
                progressBarHeadDamage.progress = 0
                progressBarLegDamage.progress = 0
                headDamage.visibility = View.GONE
                legDamage.visibility = View.GONE
                bodyDamage.visibility = View.GONE
                txtHead.visibility = View.GONE
                txtBody.visibility = View.GONE
                txtLeg.visibility = View.GONE
                progressBarLegDamage.visibility = View.GONE
                progressBarHeadDamage.visibility = View.GONE
                progressBarBodyDamage.visibility = View.GONE
                damageStats.visibility = View.INVISIBLE
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}