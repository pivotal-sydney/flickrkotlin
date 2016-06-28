package com.example.pivotal.flickrkotlin.service

import com.example.pivotal.flickrkotlin.model.FlickrImages
import retrofit2.Call
import retrofit2.http.GET

interface FlickrService {
    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    fun getImages(): Call<FlickrImages>
}