package com.example.plants

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.plants.models.PlantInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.plant_list_item.view.*

class PlantAdapter(val contextActivity: Context, val array: Array<PlantInfo>) : ArrayAdapter<PlantInfo>(contextActivity, 0, array) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = convertView?: LayoutInflater.from(contextActivity).inflate(R.layout.plant_list_item, parent, false)

        val plantItem = getItem(position)!!

        layout.plantName.text = plantItem.plant.name

        layout.setOnClickListener {
            val intent = Intent(contextActivity, PlantDetailActivity::class.java)
            intent.putExtra("plant", Gson().toJson(plantItem))
            contextActivity.startActivity(intent)
        }

        return layout
    }
}