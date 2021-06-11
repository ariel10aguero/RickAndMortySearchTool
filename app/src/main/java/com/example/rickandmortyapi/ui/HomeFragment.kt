package com.example.rickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.databinding.FragmentHomeBinding
import com.example.rickandmortyapi.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLocation(type = "io")
        viewModel.getEpisode(episode = "S04E09")

        setUpCharacterObserver()
        setUpLocationObserver()
        setUpEpisodeObserver()

        setCharacterSearchView()

        binding.characterSearchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

    }

    private fun setUpCharacterObserver(){
        viewModel.characterState.observe(viewLifecycleOwner, Observer {characterState ->
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
        viewModel.locationState.observe(viewLifecycleOwner, Observer {locationState ->
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
        viewModel.episodeState.observe(viewLifecycleOwner, Observer {episodeState ->
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

    private fun setCharacterSearchView(){
        binding.characterSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                view
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


}