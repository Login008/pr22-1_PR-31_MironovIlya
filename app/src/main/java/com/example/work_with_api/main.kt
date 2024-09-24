package com.example.work_with_api

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject


class main : AppCompatActivity() {
    lateinit var city1 : EditText
    lateinit var temp1 : TextView
    lateinit var city_name : TextView
    lateinit var press : TextView
    lateinit var wind : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        city1 = findViewById(R.id.city1)

        temp1 = findViewById(R.id.temp)
        city_name = findViewById(R.id.city)
        press = findViewById(R.id.press)
        wind = findViewById(R.id.wind)
    }

    fun DoResult(view: View) {
        if (city1.text.toString().isNotEmpty())
        {
            var key = "51eb3729019da89a7179328ed4dd0997"
            var url = "https://api.openweathermap.org/data/2.5/weather?q=" + city1.text.toString().toLowerCase() + "&appid=" + key + "&units=metric&lang=ru"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                com.android.volley.Request.Method.GET,
                url,
                {
                        responce ->
                    val obj = JSONObject(responce)

                    val temp = obj.getJSONObject("main")
                    temp1.text = "Temperature: " + temp.getString("temp") + " C"

                    city_name.text = "City: " + obj.getString("name")

                    press.text = "Air pressure: " + temp.getString("pressure") + " mm"

                    val wind1 = obj.getJSONObject("wind")
                    wind.text = "Wind speed: " + wind1.getString("speed") + " m/s"
                },
                {
                    Snackbar.make(view, R.string.warn1, Snackbar.LENGTH_LONG).show()
                }
            )
            queue.add(stringRequest)
        }
        else
        {
            Snackbar.make(view, R.string.warn, Snackbar.LENGTH_LONG).show()
        }
    }
}