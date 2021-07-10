package com.example.danielfaronbi.personalfinance

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class GlobalClass: Application() {

    var received_data = JSONObject()
    val url = "https://script.google.com/macros/s/AKfycbx6UvcAycDplhSkZMmN3CZPbfu_QXYBHk9tQArj46nP2aTVImyoZ6GjEbEssD_Rt6KM/exec?"

    fun volley_post(parameters:JSONObject, context: Context) {

        val myQueue: RequestQueue = Volley.newRequestQueue(context)

        val pkeys = parameters.keys()
        var postLink = url

        for(key in pkeys){
            postLink += key + "=" + parameters.get(key) + "&"
        }
        var message:String = ""

        // Request a string response from the provided URL.
        val request = StringRequest(
            Request.Method.GET, postLink,
            { response ->
                // Display the first 500 characters of the response string.
                Toast.makeText(context, "Response Submitted! ", Toast.LENGTH_LONG).show()
            },
            { Toast.makeText(context, "Error!! ", Toast.LENGTH_LONG).show()})

        myQueue.add(request)
    }

}