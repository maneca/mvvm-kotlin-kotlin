package com.example.mvvm_kotlin_kotlin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.viewmodel.CountriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {
    private val countriesViewModel by viewModel<CountriesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

         return ComposeView(requireContext()).apply {
            setContent {
                val countries = countriesViewModel.countriesList.value
                CountriesList(countries)
            }
        }

    }

    @Composable
    fun CountriesList(countries: List<CountriesData>){
        LazyColumn(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
            items(countries) { country ->
                CountryItem(country = country)
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    fun CountryItem(country: CountriesData){
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            country.flag?.let { url ->
                loadPicture(url = url)
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = country.name!!)

        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun loadPicture(url: String){
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .componentRegistry {
                add(SvgDecoder(LocalContext.current))
            }
            .build()

        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val painter = rememberImagePainter(url)

            Image(
                painter = painter,
                contentDescription = "SVG Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}