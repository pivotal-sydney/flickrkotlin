package com.example.pivotal.flickrkotlin.model

import java.util.*

class FlickrImages {
    var items: List<FlickrImage> = ArrayList()
}

class FlickrImage {
    var title: String = ""
    var media: Media = Media()
}

class Media {
    var m: String = ""
}
