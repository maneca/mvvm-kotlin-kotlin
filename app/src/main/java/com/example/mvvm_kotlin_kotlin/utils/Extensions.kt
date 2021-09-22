package com.example.mvvm_kotlin_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.mvvm_kotlin_kotlin.R

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}

@ExperimentalCoilApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun LoadPicture(url: String, fullscreen: Boolean = false){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
        val painter = rememberImagePainter(url)

        Image(
            painter = painter,
            contentDescription = "Country flag",
            modifier = Modifier
                .size(width = if(fullscreen) 300.dp else 80.dp, height = if(fullscreen) 200.dp else 80.dp)
                .clip(if(fullscreen) RectangleShape else CircleShape),
            contentScale = ContentScale.FillBounds
        )
    }
}