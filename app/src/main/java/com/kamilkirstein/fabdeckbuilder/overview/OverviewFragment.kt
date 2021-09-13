/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kamilkirstein.fabdeckbuilder.overview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kamilkirstein.fabdeckbuilder.R
import com.kamilkirstein.fabdeckbuilder.databinding.FragmentOverviewBinding
import com.kamilkirstein.fabdeckbuilder.datafilter.KeyWords


/**
 * This fragment shows the the status of the Mars photos web services transaction.
 */
class OverviewFragment : Fragment(), OnClickListener {

    private val viewModel: OverviewViewModel by viewModels()
    // array for the class selection spinner
    val classes = arrayListOf<KeyWords>(
        KeyWords.KEYWORDS_ALL,
        KeyWords.KEYWORDS_GENERIC,
        KeyWords.KEYWORDS_BRUTE,
        KeyWords.KEYWORDS_GUARDIAN,
        KeyWords.KEYWORDS_MECCHANOLOGIST,
        KeyWords.KEYWORDS_NINJA,
        KeyWords.KEYWORDS_RANGER,
        KeyWords.KEYWORDS_RUNEBLADE,
        KeyWords.KEYWORDS_WARRIOR,
        KeyWords.KEYWORDS_WIZARD
    )
    val talents = arrayListOf<KeyWords>(
        KeyWords.KEYWORDS_LIGHT,
        KeyWords.KEYWORDS_SHADOW,
        KeyWords.KEYWORDS_ELEMENTAL,
        KeyWords.KEYWORDS_EARTH,
        KeyWords.KEYWORDS_ICE,
        KeyWords.KEYWORDS_LIGHTNING
    )
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        // set the  onClicklistener to the buttons
        binding.btnNextPage.setOnClickListener(this);
        binding.btnPrevPage.setOnClickListener(this);

        // spinner for set selection
        val spinnerSets = binding.spinnerSets
        if (spinnerSets != null) {

            val adapter = activity?.let {
                ArrayAdapter.createFromResource(
                    it?.baseContext, R.array.Sets,
                    android.R.layout.simple_spinner_item
                )
            }

            if (adapter != null) {
                spinnerSets.adapter = adapter
            }
            val sets = R.array.Sets
            spinnerSets.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    // when we change the set the page should be set to 1 again
                    viewModel.setPageNumber(1)
                    if (spinnerSets.selectedItem.toString() == "ALL")
                        viewModel.setSet(null)
                    else
                        viewModel.setSet(spinnerSets.selectedItem.toString())

                    viewModel.getCardsOfSetForPageWithKeywords(
                        viewModel.pageNumber(),
                        viewModel.set(),
                        viewModel._cardFilter.getKeyWordsStringFromKeyWords()
                    )
                    // set the color of the first item to wihte
                    (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }
        }

        // spinner for Class selection
        val spinnerClasses = binding.spinnerClasses
        if (spinnerClasses != null) {

            val adapter = activity?.let {
                ArrayAdapter(
                    it?.baseContext,
                    android.R.layout.simple_spinner_item,
                    classes.toArray()
                )
            }

            if (adapter != null) {
                spinnerClasses.adapter = adapter
            }
            spinnerClasses.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    // when we change the class the page should be set to 1 again
                    viewModel.setPageNumber(1)
                    // clear the list to set just one parameter at the tie for the keywords (for now)
                    viewModel._cardFilter.m_keyWords.clear()
                    viewModel._cardFilter.m_keyWords.add(spinnerClasses.selectedItem as KeyWords)
                    viewModel.getCardsOfSetForPageWithKeywords(
                        viewModel.pageNumber(),
                        viewModel.set(),
                        viewModel._cardFilter.getKeyWordsStringFromKeyWords()
                    )
                    // set the color of the first item to wihte
                    (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)

                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNextPage -> {
                viewModel.nextPage()
                // viewModel.getCardsForPage(viewModel.pageNumber())
                viewModel.getCardsOfSetForPageWithKeywords(
                    viewModel.pageNumber(),
                    viewModel.set(),
                    viewModel._cardFilter.getKeyWordsStringFromKeyWords()
                )
            }
            R.id.btnPrevPage -> {
                viewModel.prevPage()
                // viewModel.getCardsForPage(viewModel.pageNumber())
                viewModel.getCardsOfSetForPageWithKeywords(
                    viewModel.pageNumber(),
                    viewModel.set(),
                    viewModel._cardFilter.getKeyWordsStringFromKeyWords()
                )
            }
            else -> {
            }
        }
    }
}



