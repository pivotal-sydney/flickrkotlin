package com.example.pivotal.flickrkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.pivotal.flickrkotlin.adapter.FlickrAdapter.FlickrViewHolder
import com.example.pivotal.flickrkotlin.model.FlickrImage
import com.example.pivotal.flickrkotlin.model.FlickrImages
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import java.util.*

class FlickrAdapter(val picasso: Picasso) : RecyclerView.Adapter<FlickrViewHolder>() {
    val ID_IMAGE: Int = View.generateViewId()

    val ID_TEXT: Int = View.generateViewId()

    var flickrImages: List<FlickrImage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FlickrViewHolder? {
        val text: LinearLayout = with(parent!!) {
            verticalLayout {
                imageView {
                    id = ID_IMAGE
                }

                textView {
                    id = ID_TEXT
                }
            }
        }

        return FlickrViewHolder(text)
    }

    override fun getItemCount(): Int {
        return flickrImages.size
    }

    fun setFlickrImage(flickrImages: FlickrImages) {
        this.flickrImages = flickrImages.items!!
    }

    override fun onBindViewHolder(holder: FlickrViewHolder?, position: Int) {
        val url = flickrImages.get(position).media!!.m
        val text = flickrImages.get(position).title
        picasso.load(url).into(holder!!.image)
        holder.text.text = text
    }


    inner class FlickrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val text: TextView

        init {
            image = itemView.find(ID_IMAGE)
            text = itemView.find(ID_TEXT)
        }
    }
}
