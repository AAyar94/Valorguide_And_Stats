package com.aayar94.valorguidestats.data.models.game_content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Spray(
    val animationGif: String?,
    val animationPng: String?,
    val category: String?,
    val displayIcon: String,
    val displayName: String,
    val fullIcon: String?,
    val fullTransparentIcon: String?,
    val isNullSpray: Boolean,
) : Parcelable