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


    }

}