package com.example.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.databinding.ActivityMainBinding
import com.example.rickandmortyapi.ui.MainViewModel
import com.example.rickandmortyapi.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getLocation(type = "io")
        viewModel.getEpisode(episode = "S04E10")

        setUpCharacterObserver()
        setUpLocationObserver()
        setUpEpisodeObserver()

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
                    Log.d("loading Location", "Loading Location")

                }
                is DataState.Success<LocationResponse> -> {
                    Log.d("location", "${locationState.data.results}")
                }
                is DataState.Error -> {
                    Log.d("errorLocation", "${locationState.exception}")
                }
            }
        })
    }

    private fun setUpEpisodeObserver(){
        viewModel.episodeState.observe(this, Observer {episodeState ->
            when(episodeState){
                is DataState.Loading -> {
                    Log.d("loading", "Loading Episode")
                }
                is DataState.Success<EpisodeResponse> -> {
                    Log.d("episode", "${episodeState.data.results}")
                }
                is DataState.Error -> {
                    Log.d("error", "${episodeState.exception}")
                }
            }
        })
    }


}