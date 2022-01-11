package com.example.vancouver_transport

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.json.JSONArray
import org.json.JSONObject

class APIClient(cio : CIO) {
    private val client : HttpClient = HttpClient(cio)

    suspend fun getSH(id : Int) : SuperHero{
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/$id")
        val body: String = httpResponse.receive()
        // is it the right place to parse ? (separation of concerns)
        return ParseSuperHero.fromJson(body)
    }

    suspend fun getFirstSH(firsts : Int): ArrayList<SuperHero>{
        val list = ArrayList<SuperHero>()
        var max = firsts

        if(max > 731){
            max = 731
        }

        for (i in 1..firsts) {
            list.add(getSH(i))
        }

        return list
    }

    suspend fun getSH(name: String): ArrayList<SuperHero>{
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/search/$name")
        val listSH = ArrayList<SuperHero>()

        if(httpResponse.status == HttpStatusCode.OK) {
            //val jsonBody = JSONObject(httpResponse.receive<String>())
            //val results = JSONArray(jsonBody.get("results"))
            val jsonBody : JsonElement = Json.decodeFromString(JsonElement.serializer(),httpResponse.receive())

            Log.println(Log.INFO,"TEST",jsonBody.toString())
            val results = Json.decodeFromString(JsonElement.serializer(),jsonBody.jsonObject["results"].toString())

            //results.jsonObject.forEach()

        }
        return listSH
    }
}