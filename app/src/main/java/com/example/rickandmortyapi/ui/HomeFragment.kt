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
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()


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

        Log.d("homevm", "$viewModel")
        var filterCharacter: String = "name"
        var filterLocation: String = "name"
        var filterEpisode: String = "name"


        fun radioButtonsFilters() {
            binding.apply {
                characterRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        characterNameBtn.id -> filterCharacter = "name"
                        characterStatusBtn.id -> filterCharacter = "status"
                        characterSpecieBtn.id -> filterCharacter = "species"
                        characterGenderBtn.id -> filterCharacter = "gender"
                    }
                }
                locationRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        locationNameBtn.id -> filterLocation = "name"
                        locationTypeBtn.id -> filterLocation = "type"
                        locationDimensionBtn.id -> filterLocation = "dimension"
                    }
                }
                episodeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        episodeNameBtn.id -> filterEpisode = "name"
                        episodeEpisodeBtn.id -> filterEpisode = "episode"
                    }
                }
            }
        }
        radioButtonsFilters()

        fun searchCharacter() {
            binding.apply {
                characterSearchBtn.setOnClickListener {
                    val userInput = characterSearchView.text.toString()
                    val query = mapOf(filterCharacter to userInput)
                    viewModel.getCharacter(query)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
            }
        }
        searchCharacter()

        fun searchLocation(){
            binding.apply {
                locationSearchBtn.setOnClickListener {
                    val userInput = locationSearchView.text.toString()
                    val query = mapOf(filterLocation to userInput)
                    viewModel.getLocation(query)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
            }
        }
        searchLocation()

        fun searchEpisode(){
            binding.apply {
                episodeSearchBtn.setOnClickListener {
                    val userInput = episodeSearchView.text.toString()
                    val query = mapOf(filterEpisode to userInput)
                    viewModel.getEpisode(query)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
            }
        }
        searchEpisode()
    }
}