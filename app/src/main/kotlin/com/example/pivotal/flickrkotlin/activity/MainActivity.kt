package com.example.pivotal.flickrkotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.pivotal.flickrkotlin.adapter.FlickrAdapter
import com.example.pivotal.flickrkotlin.model.FlickrImages
import com.example.pivotal.flickrkotlin.service.FlickrService
import com.squareup.picasso.Picasso
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flickrAdapter = FlickrAdapter(Picasso.with(this))
        val flickrService: FlickrService = Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FlickrService::class.java)

        val call: Call<FlickrImages> = flickrService.getImages()
        call.enqueue(object: Callback<FlickrImages> {
            override fun onFailure(call: Call<FlickrImages>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<FlickrImages>?, response: Response<FlickrImages>?) {
                flickrAdapter.setFlickrImage(response!!.body())
            }
        })

        verticalLayout {
            textView {
                text = "hello world!"
            }

            recyclerView {
                adapter = flickrAdapter
                layoutManager = GridLayoutManager(context, 3)
            }
        }

    }
}

