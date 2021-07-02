package com.example.plants

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plants.models.PlantInfo
import com.example.plants.models.ResponseResult
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()

        getPlantInfo()
    }

    private fun getPlantInfo() {
        progressBar.visibility = View.VISIBLE
        plantListView.visibility = View.GONE

        runBlocking {
            val (request, response, result) = Fuel.get("https://mocki.io/v1/981a9d59-2cc9-44b9-8b7f-3aafc3358211").awaitStringResponseResult()

            result.fold(
                {
                    var (data, _) = result

                    val sType = object : TypeToken<ResponseResult>() { }.type
                    val responseResult: ResponseResult = Gson().fromJson(data.toString(), sType)

                    val plantArray = responseResult.results

                    drawPlants(plantArray)
                },
                { val (_, error) = result
                    Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_LONG)
                }
            )
        }
    }

    private fun drawPlants(plantArray: Array<PlantInfo>) {
        progressBar.visibility = View.GONE
        plantListView.visibility = View.VISIBLE

        plantListView.adapter = PlantAdapter(this, plantArray)
    }
}