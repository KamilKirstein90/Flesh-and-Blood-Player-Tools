package com.kamilkirstein.fabdeckbuilder.ui.overview


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkirstein.fabdeckbuilder.datafilter.CardFilter
import com.kamilkirstein.fabdeckbuilder.network.FabDbAPI
import com.kamilkirstein.fabdeckbuilder.network.data.Data
import kotlinx.coroutines.launch

enum class FabDBAPIStatus { LOADING, ERROR, DONE, NOTHING_FOUND }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // the Cardfilter is the only class that will be used in the model to show different data or to
    public var _cardFilter = CardFilter()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<FabDBAPIStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<FabDBAPIStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of cards
    // with new values
    private val _cards = MutableLiveData<List<Data>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val cards: LiveData<List<Data>> = _cards

    /**
     * Call getCards() on init so we can display status immediately.
     */
    init {
        cards()
    }

    /**
     * Gets Card information from the FabDB API Retrofit service and updates the
     * [Cards] [List] [LiveData].
     */
    public fun cards() {
        viewModelScope.launch {
            _status.value = FabDBAPIStatus.LOADING
            try {

                _cards.value = FabDbAPI.retrofitService.getCards(_cardFilter._pageNumber,
                                                                _cardFilter._name,
                                                                _cardFilter._set,
                                                                _cardFilter._keyWordsString,
                                                                _cardFilter._pitch?.toString(),
                                                                _cardFilter._cost?.toString(),
                                                                _cardFilter._rarity?.toString()).cardsData

                if (_cards.value.isNullOrEmpty()) {
                    _status.value = FabDBAPIStatus.NOTHING_FOUND
                    Log.e("LogResponse", "Nothing Found");
                }
                else {
                    _status.value = FabDBAPIStatus.DONE
                }

            } catch (e: Exception) {
                Log.e("LogResponse ", e.toString());
                _status.value = FabDBAPIStatus.ERROR
                _cards.value = listOf()
            }
        }
    }
}
