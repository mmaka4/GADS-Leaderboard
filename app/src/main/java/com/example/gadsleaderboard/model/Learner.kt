package com.example.gadsleaderboard.model

import com.google.gson.annotations.SerializedName

class Learner{

    @SerializedName("name")
    var name:String? = null
    @SerializedName("hours")
    var hours: Int? = null
    @SerializedName("country")
    var country:String? = null
    @SerializedName("badgeUrl")
    var badgeUrl:String? = null
}
