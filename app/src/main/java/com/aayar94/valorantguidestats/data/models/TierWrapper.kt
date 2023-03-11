package com.aayar94.valorantguidestats.data.models

import com.google.gson.annotations.SerializedName


data class TierWrapper(

    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("assetObjectName") var assetObjectName: String? = null,
    @SerializedName("tiers") var tiers: ArrayList<Tiers> = arrayListOf(),
    @SerializedName("assetPath") var assetPath: String? = null

)