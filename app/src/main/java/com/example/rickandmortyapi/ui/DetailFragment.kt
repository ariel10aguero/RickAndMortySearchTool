package com.example.rickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.*
import com.example.rickandmortyapi.databinding.FragmentDetailBinding
import com.example.rickandmortyapi.databinding.FragmentHomeBinding
import com.example.rickandmortyapi.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("detailvm", "$viewModel")

        setUpCharacterObserver()
        setUpLocationObserver()
        setUpEpisodeObserver()

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }


     private fun setUpCharacterObserver(){
        viewModel.characterState.observe(viewLifecycleOwner, Observer {characterState ->
            when(characterState){
                is DataState.Loading -> {
                    Log.d("loading", "Loading Character")
                }
                is DataState.Success<CharacterResponse> -> {
                    val result = characterState.data.results
                    bindCharacter(result[0])
                    setUpArrows(result)
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
                    val result = locationState.data.results
                    bindLocation(result[0])
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

    private fun bindCharacter(character: Character){
        binding.apply {
            nameTxt.text = character.name
            detailTxtOne.text = "Status: ${character.status}"
            detailTxtTwo.text = "Spicies ${character.species}"
            detailTxtThree.text = "Gender: ${character.gender}"
            detailTxtFour.text = "Origin: ${character.origin["name"]}"
            detailTxtFive.text = "Location: ${character.location["name"]}"
            Glide.with(this@DetailFragment)
                .load(character.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(binding.imageView)
        }
    }
    private fun bindLocation(location: Location){
        binding.apply {
            nameTxt.text = location.name
            detailTxtOne.text = "Type: ${location.type}"
            detailTxtTwo.text = "Dimension: ${location.residents}"
        }
    }

    private fun setUpArrows(characterList: ArrayList<Character>){
        var position = 0
        binding.apply {
            nextBtn.setOnClickListener {
                if(position < characterList.lastIndex){
                    position++
                    bindCharacter(characterList[position])
                }
            }
            backBtn.setOnClickListener {
                if(position > 0){
                    position--
                    bindCharacter(characterList[position])
                }
            }
        }
    }
}

