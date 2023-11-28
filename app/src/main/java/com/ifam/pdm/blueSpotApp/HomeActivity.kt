package com.ifam.pdm.blueSpotApp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifam.pdm.blueSpotApp.core.entities.Property
import com.ifam.pdm.blueSpotApp.core.enums.PropertyType
import com.ifam.pdm.blueSpotApp.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private val BASE_URL = "https://blue-spot-kotlin-api-production.up.railway.app/"
    private lateinit var propriedadesRecyclerView: RecyclerView
    private val properties: MutableList<Property> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
        pegarPropriedades()

//        properties.add(aTestProperty(true, true))
//        properties.add(aTestProperty(true, false))
//        properties.add(aTestProperty(false, true))
//        properties.add(aTestProperty(false, false))

        val adapter = PropriedadeAdapter(properties)
        propriedadesRecyclerView.adapter = adapter
    }

    private fun init() {
        propriedadesRecyclerView = findViewById(R.id.propriedade_rv)
        propriedadesRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
    }

    private fun pegarPropriedades() {
        val retrofitClient =
            NetworkUtils.getRetrofitInstance(BASE_URL).create(ApiRequests::class.java)
        retrofitClient.pegarPropriedades().enqueue(object : retrofit2.Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                response.body()?.let {
                    properties.addAll(it)
                    propriedadesRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                val toast = Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

//    private fun aTestProperty(garage: Boolean, verified: Boolean): Property {
//        return Property(
//            id = "123456",
//            landlordId = "789",
//            images = listOf("image1.jpg", "image2.jpg", "image3.jpg"),
//            description = "A spacious property with a beautiful view",
//            address = "123 Main Street, City, Country",
//            price = 1500.0,
//            propertyType = PropertyType.RESIDENCIAL,
//            restrictions = mutableListOf("No pets", "No smoking"),
//            furnishings = mutableListOf("Sofa", "Bed", "Dining table"),
//            hasGarage = garage,
//            isAvailable = true,
//            isVerified = verified
//        )
//    }
}