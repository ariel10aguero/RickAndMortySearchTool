package com.example.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.ui.MainViewModel
import com.example.rickandmortyapi.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewChar = findViewById<TextView>(R.id.characterTxt)

        viewModel.getLocation(type = "Asteroid")
        setUpCharacterObserver()
        setUpLocationObserver()

    }

    private fun setUpCharacterObserver(){
        viewModel.characterState.observe(this, Observer {characterState ->
            when(characterState){
                is DataState.Loading -> {
                    Log.d("loading", "Loading Character")
                }
                is DataState.Success<CharacterResponse> -> {
                    Log.d("character", "${characterState.data.results}")
                }
                is DataState.Error -> {
                    Log.d("error", "${characterState.exception}")
                }
            }
        })
    }

    private fun setUpLocationObserver(){
        viewModel.locationState.observe(this, Observer {locationState ->
            when(locationState){
                is DataState.Loading -> {
                    Log.d("loading", "Loading Location")

                }
                is DataState.Success<LocationResponse> -> {
                    Log.d("location", "${locationState.data.results}")
                }
                is DataState.Error -> {
                    Log.d("error", "${locationState.exception}")
                }
            }
        })
    }



}