package com.example.mvvm_kotlin_kotlin.ui

import com.example.mvvm_kotlin_kotlin.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.annotation.ExperimentalCoilApi
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.utils.LoadPicture
import com.example.mvvm_kotlin_kotlin.viewmodel.CountriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: CountriesData) = CountriesDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("country_data_row", data)
            }
        }
    }

    private val countriesViewModel by viewModel<CountriesViewModel>()

    @ExperimentalCoilApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val country: CountriesData? = arguments?.getParcelable("country_data_row")

        return ComposeView(requireContext()).apply {
            setContent {
                if (country != null)
                    MainContent(country)
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    fun MainContent(country: CountriesData) {
        val scrollState = rememberScrollState()
        val (isChecked, setChecked) = remember { mutableStateOf(country.isFavourite) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(scrollState, true)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                country.flag?.let { url ->
                    LoadPicture(url = url, true)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                country.name?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h4
                        )
                        FavoriteButton(
                            isChecked = isChecked,
                            onClick = {
                                setChecked(!isChecked)
                                countriesViewModel.updateFavourite(country.id, !isChecked)
                                country.isFavourite = !isChecked
                            }
                        )
                    }
                    country.capital?.let { capital ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_capital),
                            detailValue = capital
                        )
                    }
                    country.currencies?.let { currencies ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_currency),
                            detailValue = currencies.joinToString { "${it.name!!} (${it.symbol})" }
                        )
                    }
                    country.region?.let { region ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_region),
                            detailValue = region
                        )
                    }
                    country.subregion?.let { subregion ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_sub_region),
                            detailValue = subregion
                        )
                    }
                    country.area?.let { area ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_area),
                            detailValue = "$area km2")
                    }
                    country.borders?.let { borders ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_borders),
                            detailValue = borders.joinToString())
                    }
                    country.languages?.let { languages ->
                        TextDetailWidget(
                            detailName = stringResource(R.string.country_language),
                            detailValue = languages.joinToString { "${it.name!!} (${it.nativeName})" })
                    }
                }

            }
        }
    }

    @Composable
    fun TextDetailWidget(detailName: String, detailValue: String) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$detailName ")
                }
                append(detailValue)
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = TextStyle(fontSize = 15.sp)
        )
    }
}