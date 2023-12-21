package com.aayar94.valorguidestats.data.models.game_content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weapon(
    val uuid: String,
    val displayName: String,
    val category: String,
    val defaultSkinUuid: String,
    val killStreamIcon: String,
    val assetPath: String,
    val displayIcon: String,
    val weaponStats: WeaponStats?,
    val shopData: WeaponShopData?,
    val skins: Array<WeaponSkin>
) : Parcelable

@Parcelize
class WeaponStats(
    val damageRanges: Array<WeaponDamageRange?>
) : Parcelable

@Parcelize
class WeaponShopData(
    val cost: Int,
    val category: String,
    val categoryText: String?,
    val image: String,
    val newImage: String,
    val newImage2: String,
    val assetPath: String
) : Parcelable

@Parcelize
class WeaponDamageRange(
    val headDamage: Float?,
    val bodyDamage: Float?,
    val legDamage: Float?
) : Parcelable

@Parcelize
class WeaponSkin(
    val uuid: String,
    val displayName: String,
    val themeUuid: String,
    val contentTierUuid: String,
    val displayIcon: String,
    val chromas: Array<WeaponSkinChroma>,
    val levels: Array<WeaponSkinLevel>
) : Parcelable

@Parcelize
class WeaponSkinChroma(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val fullRender: String,
) : Parcelable

@Parcelize
class WeaponSkinLevel(
    val uuid: String,
    val displayName: String,
    val displayIcon: String,
    val streamedVideo:String?,
) : Parcelable