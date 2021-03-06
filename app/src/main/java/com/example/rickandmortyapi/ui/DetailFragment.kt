package com.example.rickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.*
import com.example.rickandmortyapi.databinding.FragmentDetailBinding
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
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("loading", "Loading Character")
                }
                is DataState.Success<CharacterResponse> -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.titleTypeTxt.text = "Character"
                    val result = characterState.data.results
                    bindCharacter(result[0])
                    setUpCharacterArrows(result)
                    Log.d("character", "${characterState.data.results}")
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.imageView.setImageResource(R.drawable.search_not_found)
                    binding.nameTxt.text = "Character not found"
                    Log.d("error", "${characterState.exception}")
                }
            }
        })
    }

    private fun setUpLocationObserver(){
        viewModel.locationState.observe(viewLifecycleOwner, Observer {locationState ->
            when(locationState){
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("loading Location", "Loading Location")
                }
                is DataState.Success<LocationResponse> -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.titleTypeTxt.text = "Location"
                    binding.imageView.setImageResource(R.drawable.location_detail)
                    val result = locationState.data.results
                    bindLocation(result[0])
                    setUpLocationArrows(result)
                    Log.d("location", "${locationState.data.results}")
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.imageView.setImageResource(R.drawable.search_not_found)
                    binding.nameTxt.text = "Location not found"
                    Log.d("errorLocation", "${locationState.exception}")
                }
            }
        })
    }

    private fun setUpEpisodeObserver(){
        viewModel.episodeState.observe(viewLifecycleOwner, Observer {episodeState ->
            when(episodeState){
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("loading", "Loading Episode")
                }
                is DataState.Success<EpisodeResponse> -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.titleTypeTxt.text = "Episode"
                    binding.imageView.setImageResource(R.drawable.episode_detail)
                    val result = episodeState.data.results
                    bindEpisode(result[0])
                    setUpEpisodeArrows(result)
                    Log.d("episode", "${episodeState.data.results}")
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.imageView.setImageResource(R.drawable.search_not_found)
                    binding.nameTxt.text = "Episode not found"
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
            // Asegurarse de limpiar la vista despues de haber bindeado
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
            detailTxtTwo.text = "Dimension: ${location.dimension}"
        }
    }
    private fun bindEpisode(episode: Episode){
        binding.apply {
            nameTxt.text = episode.name
            detailTxtOne.text = "Air Date: ${episode.air_date}"
            detailTxtTwo.text = "Episode ${episode.episode}"
        }
    }

    private fun setUpCharacterArrows(characterList: ArrayList<Character>){
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

    private fun setUpLocationArrows(locationList: ArrayList<Location>){
        var position = 0
        binding.apply {
            nextBtn.setOnClickListener {
                if(position < locationList.lastIndex){
                    position++
                    bindLocation(locationList[position])
                }
            }
            backBtn.setOnClickListener {
                if(position > 0){
                    position--
                    bindLocation(locationList[position])
                }
            }
        }
    }

    private fun setUpEpisodeArrows(episodeList: ArrayList<Episode>){
        var position = 0
        binding.apply {
            nextBtn.setOnClickListener {
                if(position < episodeList.lastIndex){
                    position++
                    bindEpisode(episodeList[position])
                }
            }
            backBtn.setOnClickListener {
                if(position > 0){
                    position--
                    bindEpisode(episodeList[position])
                }
            }
        }
    }

    private fun cleanTxt(){
        binding.apply {
            imageView.setImageResource(0)
            titleTypeTxt.text = ""
            nameTxt.text = ""
            detailTxtOne.text = ""
            detailTxtTwo.text = ""
            detailTxtThree.text = ""
            detailTxtFour.text = ""
            detailTxtFive.text = ""
        }
    }

    override fun onResume() {
        cleanTxt()
        super.onResume()
    }
}

