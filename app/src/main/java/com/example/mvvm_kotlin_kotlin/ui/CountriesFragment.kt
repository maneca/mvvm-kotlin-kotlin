package com.example.mvvm_kotlin_kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.example.mvvm_kotlin_kotlin.R
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.utils.LoadPicture
import com.example.mvvm_kotlin_kotlin.viewmodel.CountriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {
    private val countriesViewModel by viewModel<CountriesViewModel>()

    @ExperimentalCoilApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         return ComposeView(requireContext()).apply {
            setContent {
                val countries = countriesViewModel.countriesList.value
                val loading = countriesViewModel.showLoading.get()
                val error = countriesViewModel.showError.value

                when{
                    loading ->
                        CircularProgressIndicator(modifier = Modifier
                        .wrapContentSize(),
                        color = Color.Blue,
                        strokeWidth = 5.dp)
                    !error.isNullOrBlank() ->
                        ErrorDialog(message = error)
                    else ->
                        Scaffold(
                            bottomBar = { AppBottomBar() }
                        ){
                            CountriesList(countries)
                        }
                }
            }
        }

    }

    @ExperimentalCoilApi
    @Composable
    fun CountriesList(countries: List<CountriesData>){
        LazyColumn(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
        ) {
            items(countries) { country ->
                CountryItem(country = country)
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    fun CountryItem(country: CountriesData){
        Card(
            modifier = Modifier
                .background(shape = RoundedCornerShape(10.dp), color = Color.LightGray)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(2.dp)
                .clickable {
                    val bundle = Bundle()
                    bundle.putParcelable("country_data_row", country)
                    findNavController().navigate(R.id.view_country_details, bundle)
                },
            elevation = 4.dp,
        ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically){
                country.flag?.let { url ->
                    LoadPicture(url = url)
                }
                Spacer(modifier = Modifier.size(4.dp))
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()) {
                    Text(text = country.name!!, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.country_capital)+ " ")
                            }
                            append(country.capital!!)
                        })
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.country_currency)+ " ")
                            }
                            append(country.currencies!!.joinToString { it.name!! })
                        })
                }
            }
        }
    }

    @Preview
    @Composable
    fun AppBottomBar() {
        val selectedState = remember { mutableStateOf(0) }

        BottomAppBar(
            elevation = 10.dp,
            backgroundColor = Color.Blue
        ) {
            BottomNavigationItem(
                icon= {
                    Icon(Icons.Filled.List,"")
                },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    selectedState.value = 0
                    countriesViewModel.getAllCountries()
                },
                selected = selectedState.value == 0
            )

            BottomNavigationItem(
                icon= {
                    Icon(Icons.Filled.Favorite,"")
                },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    selectedState.value = 1
                    countriesViewModel.getFavourites()
                },
                selected = selectedState.value == 1
            )
        }
    }

    @Composable
    fun ErrorDialog(message: String){
        val openDialog = remember { mutableStateOf(false)  }

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = getString(R.string.error_title))
            },
            text = {
                Text(message)
            },
            confirmButton = {
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(getString(R.string.dismiss_button))
                }
            }
        )
    }
}