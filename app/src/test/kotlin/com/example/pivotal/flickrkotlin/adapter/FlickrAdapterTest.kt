package com.example.pivotal.flickrkotlin.adapter

import com.example.pivotal.flickrkotlin.BuildConfig
import com.example.pivotal.flickrkotlin.model.FlickrImage
import com.squareup.picasso.Picasso
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(18))
class FlickrAdapterTest {

    val subject: FlickrAdapter = FlickrAdapter(Picasso.with(RuntimeEnvironment.application))

    @Test
    fun itemCount_hasSizeOfFlickrImages() {
        Assertions.assertThat(subject.itemCount).isEqualTo(0)

        val flickrImages = ArrayList<FlickrImage>()

        subject.flickrImages
    }

}