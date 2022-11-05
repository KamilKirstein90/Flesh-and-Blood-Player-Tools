package com.kamilkirstein.fabdeckbuilder.ui.game

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.kamilkirstein.fabdeckbuilder.R
import com.kamilkirstein.fabdeckbuilder.databinding.DialogFragmentStartGameBinding
import com.kamilkirstein.fabdeckbuilder.databinding.FragmentGameBinding

class StartGameDialogFragment : DialogFragment(), AdapterView.OnItemSelectedListener {

    // binding object for custom R.LayoutDialog
    private lateinit var binding: DialogFragmentStartGameBinding
    private lateinit var gameFormatAdapter: ArrayAdapter<CharSequence>
    private lateinit var opponentHeroAdapter: ArrayAdapter<CharSequence>
    private lateinit var playerHeroAdapter: ArrayAdapter<CharSequence>
    private val gameViewModel :GameViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            // get layout inflater to inflate the custom view for the dialog
            val inflater = requireActivity().layoutInflater;
            binding = DialogFragmentStartGameBinding.inflate(inflater)

            setUpSpinnerAdapter()
            binding.spinnerGameFormat.adapter = gameFormatAdapter
            binding.spinnerOpponentHero.adapter = opponentHeroAdapter
            binding.spinnerPlayerHero.adapter = playerHeroAdapter


            builder
                .setView(binding.root) // pass here the custom view with binding.root
                .setMessage(R.string.title_start_game)
                .setPositiveButton(R.string.btn_label_start,
                    DialogInterface.OnClickListener { dialog, id ->
                        // START THE GAME!
                        // use the navgraph to navigate to the GameFragment
                        // pass arguments with safe Args

                    })
                .setNegativeButton(R.string.btn_label_cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setUpSpinnerAdapter() {

        gameFormatAdapter = ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.game_formats,  // array of different formats
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        opponentHeroAdapter = ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.heroes,  // array of hero names
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        playerHeroAdapter = ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.heroes,  // array of hero names
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        when (view?.id)
        {
            R.id.spinner_gameFormat -> gameViewModel.setGameMode(binding.spinnerGameFormat.selectedItem.toString())
            R.id.spinner_opponent_hero ->gameViewModel.setGameMode(binding.spinnerOpponentHero.selectedItem.toString())
            R.id.spinner_player_hero -> gameViewModel.setGameMode(binding.spinnerPlayerHero.selectedItem.toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}
