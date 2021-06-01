package com.example.flynparkmelb.model
//History schema for the database
data class History(val id: Int = -1, val user_id: Int, val date: String, val time: String, val lon: Double, val lat: Double)
