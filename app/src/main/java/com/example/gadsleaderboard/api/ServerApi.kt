package com.example.gadsleaderboard.api

import com.example.gadsleaderboard.model.Learner
import com.example.gadsleaderboard.model.Skill
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {

    @GET("/api/hours")
    fun getLearners(): Call<ArrayList<Learner>>

    @GET("/api/skilliq")
    fun getSkill(): Call<Skill>
}