package com.example.gadsleaderboard.api

import com.example.gadsleaderboard.model.Learner
import com.example.gadsleaderboard.model.Skill
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {

    @GET("/api/hours")
    fun getLearners(): Call<ArrayList<Learner>>

    @GET("/api/skilliq")
    fun getSkills(): Call<ArrayList<Skill>>

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    fun submitProject(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") projectLink: String
    ): Call<Void>
}