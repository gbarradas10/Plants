package com.example.plants.models

import java.io.Serializable

class Status(var action: String, var frequency: Frequency, var amount: Amount, var temperature: Temperature) :
    Serializable {
}