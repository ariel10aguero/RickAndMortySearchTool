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
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.data.model.CharacterResponse
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

    private fun bindCharacter(character: Character){
        binding.apply {
            nameTxt.text = character.name
            specieDimEpisodeTxt.text = character.species
            genderTxt.text = character.gender
            originTxt.text = character.origin["name"]
            locationTxt.text = character.location["name"]
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
        }
    }
}

