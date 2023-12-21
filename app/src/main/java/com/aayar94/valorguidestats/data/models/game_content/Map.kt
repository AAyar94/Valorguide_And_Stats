package com.aayar94.valorguidestats.data.models.game_content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ValorantMap(
    val uuid: String,
    val displayName: String,
    val coordinates: String?,
    val displayIcon: String,
    val listViewIcon: String,
    val splash: String,
    val assetPath: String,
    val mapUrl: String,
    val xMultiplier: Double?,
    val yMultiplier: Double?,
    val xScalarToAdd: Double?,
    val yScalarToAdd: Double?,
    val callouts: List<Callout>,
) : Parcelable

@Parcelize
data class Callout(
    val regionName: String,
    val superRegionName: String,
    val location: Location,
) : Parcelable {
    val point = convertLocationToPoint(location)

    private fun convertLocationToPoint(location: Location): Point {
        return Point(location.y, location.x)
    }
}

data class Point(
    val x: Double, val y: Double,
)

@Parcelize
data class Location(
    val x: Double,
    val y: Double,
) : Parcelable