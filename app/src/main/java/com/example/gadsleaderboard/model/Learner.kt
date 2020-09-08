package com.example.gadsleaderboard.model

import com.google.gson.annotations.SerializedName

class Foody{
    @SerializedName("id")
    var id:String? = null
    @SerializedName("name")
    var name:String? = null
    @SerializedName("price")
    var price:String? = null
    @SerializedName("image")
    var image:String? = null
}

class FoodResponse {
    @SerializedName("foods")
    var food: ArrayList<Foody>? = null
}

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
