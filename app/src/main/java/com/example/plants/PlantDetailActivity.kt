package com.example.plants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plants.models.Plant
import com.example.plants.models.PlantInfo
import com.example.plants.models.ResponseResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_plant_detail.*


class PlantDetailActivity : AppCompatActivity() {

    private lateinit var plantInfo: PlantInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val sType = object : TypeToken<PlantInfo>() { }.type
        plantInfo = Gson().fromJson(intent.getStringExtra("plant"), sType)

        plantName.text = plantInfo.plant.name
        plantDescription.text = plantInfo.plant.description
        frequencyValue.text = "${plantInfo.status.frequency.quantity} / ${plantInfo.status.frequency.period}"
        amountValue.text = "${plantInfo.status.amount.quantity} ${plantInfo.status.amount.unit}"
        temperatureValue.text = "${plantInfo.status.temperature.interval} ${plantInfo.status.temperature.unit}"
    }
}