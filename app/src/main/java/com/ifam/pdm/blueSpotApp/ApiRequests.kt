package com.ifam.pdm.blueSpotApp


import com.ifam.pdm.blueSpotApp.core.dtos.PropertyCreationDto
import com.ifam.pdm.blueSpotApp.core.dtos.UserCreationDto
import com.ifam.pdm.blueSpotApp.core.entities.Landlord
import com.ifam.pdm.blueSpotApp.core.entities.Property
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRequests {
    @POST("/landlords")
    fun cadastrarLocador(@Body user: UserCreationDto): Call<Landlord>

    @GET("/properties")
    fun pegarPropriedades(): Call<List<Property>>

    @POST("/landlords/6563c51bdeaa26303b4aaf49/properties")
    fun cadastrarPropriedade(@Body property: PropertyCreationDto): Call<Property>
}