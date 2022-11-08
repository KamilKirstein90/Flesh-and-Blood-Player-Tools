package com.kamilkirstein.fabdeckbuilder.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kamilkirstein.fabdeckbuilder.R
import com.kamilkirstein.fabdeckbuilder.databinding.FragmentGameBinding

class GameFragment : Fragment() , View.OnClickListener{

    private lateinit var binding : FragmentGameBinding
    private  val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnAddOpponent.setOnClickListener(this)
        binding.btnSubOpponent.setOnClickListener(this)
        binding.btnAddPlayer.setOnClickListener(this)
        binding.btnSubPlayer.setOnClickListener(this)

        val startGameDialogFragment  = StartGameDialogFragment()
        startGameDialogFragment.show(childFragmentManager, "Start Dialog")
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            binding.btnAddOpponent.id-> gameViewModel.addLifePointsOpponent()
            binding.btnSubOpponent.id -> gameViewModel.subLifePointsOpponent()
            binding.btnAddPlayer.id -> gameViewModel.addLifePointsPlayer()
            binding.btnSubPlayer.id -> gameViewModel.subLifePointsPlayer()
        }
    }
}