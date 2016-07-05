package com.example.pivotal.flickrkotlin.adapter

import android.widget.LinearLayout
import com.example.pivotal.flickrkotlin.BuildConfig
import com.example.pivotal.flickrkotlin.model.FlickrImage
import com.example.pivotal.flickrkotlin.model.Media
import com.squareup.picasso.Picasso
import org.assertj.core.api.Assertions
import com.nhaarman.mockito_kotlin.*
import com.squareup.picasso.RequestCreator
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(18))
class FlickrAdapterTest {

    val mockPicasso: Picasso = mock()
    val mockRequestCreator: RequestCreator = mock()
    val subject = FlickrAdapter(mockPicasso)
    val captor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)

    @Test
    fun itemCount_hasSizeOfFlickrImages() {
        Assertions.assertThat(subject.itemCount).isEqualTo(0)

        val flickrImages = Arrays.asList(FlickrImage(), FlickrImage(), FlickrImage())

        subject.flickrImages = flickrImages

        Assertions.assertThat(subject.itemCount).isEqualTo(3)
    }

    @Test
    fun createBind_bindsFlickrImagesToViewHolder() {
        var flickrImage = FlickrImage()
        flickrImage.media = Media()
        flickrImage.media.m = "http://some/request"
        flickrImage.title = "title"
        subject.flickrImages = Arrays.asList(flickrImage)

        whenever(mockPicasso.load(Mockito.anyString())).thenReturn(mockRequestCreator)
        val viewHolder = subject.onCreateViewHolder(LinearLayout(RuntimeEnvironment.application), 0)
        subject.onBindViewHolder(viewHolder, 0)

        verify(mockPicasso).load(captor.capture())

        Assertions.assertThat(captor.value).isEqualTo("http://some/request")
        verify(mockRequestCreator).into(viewHolder?.image)
    }
}