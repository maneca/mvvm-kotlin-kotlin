package com.example.mvvm_kotlin_kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData

class CountriesDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: CountriesData) = CountriesDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("country_data_row", data)
            }
        }
    }

    private var country: CountriesData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        country =  arguments?.getParcelable("country_data_row")

        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Country details", modifier = Modifier.fillMaxHeight().fillMaxWidth(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //enableBackButton()
    }

    private fun enableBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }

}