package com.aayar94.valorantguidestats.data.models.game_content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bundles(
    val displayIcon: String,
    val displayIcon2: String,
    val displayName: String,
    val displayNameSubText: String,
    val verticalPromoImage: String
) : Parcelable