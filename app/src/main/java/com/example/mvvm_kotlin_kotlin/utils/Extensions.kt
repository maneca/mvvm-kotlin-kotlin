package com.example.mvvm_kotlin_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
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