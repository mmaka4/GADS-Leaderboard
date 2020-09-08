package com.example.gadsleaderboard.model

import com.google.gson.annotations.SerializedName

class Skill {

    @SerializedName("name")
    var name:String? = null
    @SerializedName("score")
    var score: Int? = null
    @SerializedName("country")
    var country:String? = null
    @SerializedName("badgeUrl")
    var badgeUrl:String? = null
}