package com.example.plants.models

import java.io.Serializable

data class PlantInfo(var id: String, var plant: Plant, var status: Status) : Serializable {}