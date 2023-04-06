package com.aayar94.valorantguidestats.ui.fragment.weapon_details

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import com.aayar94.valorantguidestats.data.models.WeaponSkin
import com.aayar94.valorantguidestats.databinding.FragmentWeaponDetailsBinding
import kotlin.math.roundToInt


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
            fragmentList.add(WeaponSkinFragment(args.weapon.skins[index2].chromas[0].fullRender))
        }
        val adapter = WeaponSkinViewPagerAdapter(fragmentList, title, childFragmentManager)
        binding.skinViewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.skinViewPager)

    }

    private fun uriToDrawable(context: Context, uri: Uri): Drawable? {
        val inputStream = context.contentResolver.openInputStream(uri)
        return Drawable.createFromStream(inputStream, uri.toString())
    }

    private fun screenSetup() {
        with(binding) {
            weaponDetailImage.load(args.weapon.displayIcon)
            weaponDetailName.text = args.weapon.displayName
            val stringArray: Array<String> = args.weapon.category.split("::").toTypedArray()
            weaponClassText.text = stringArray[1]
            headDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.headDamage.toString()
            bodyDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.bodyDamage.toString()
            legDamage.text = args.weapon.weaponStats?.damageRanges?.get(0)?.legDamage.toString()
        }

        if (args.weapon.uuid != "2f59173c-4bed-b6c3-2191-dea9b58be9c7") {
            with(binding.progressBarHeadDamage) {
                progress = args.weapon.weaponStats?.damageRanges?.get(0)?.headDamage?.toInt()!!
                isClickable = false
            }
            with(binding.progressBarBodyDamage) {
                progress = args.weapon.weaponStats?.damageRanges?.get(0)?.bodyDamage?.toInt()!!
                isClickable = false
            }
            with(binding.progressBarLegDamage) {
                progress = args.weapon.weaponStats?.damageRanges?.get(0)?.legDamage?.toInt()!!
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