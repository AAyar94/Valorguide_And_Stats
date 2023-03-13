package com.aayar94.valorantguidestats

import com.google.gson.annotations.SerializedName


data class Tiers(

    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("assetObjectName") var assetObjectName: String? = null,
    @SerializedName("tiers") var tiers: ArrayList<TierDetail> = arrayListOf(),
    @SerializedName("assetPath") var assetPath: String? = null

)