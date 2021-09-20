package com.kamilkirstein.fabdeckbuilder.overview


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

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _cards = MutableLiveData<List<Data>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val cards: LiveData<List<Data>> = _cards

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getCards()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    public fun getCards() {
        viewModelScope.launch {
            _status.value = FabDBAPIStatus.LOADING
            try {

                _cards.value = FabDbAPI.retrofitService.getCards(_cardFilter._pageNumber,
                                                                _cardFilter._name,
                                                                _cardFilter._set,
                                                                _cardFilter._keyWordsString,
                                                                _cardFilter._pitch,
                                                                _cardFilter._cost,
                                                                _cardFilter._rarity).cardsData

                if (_cards.value.isNullOrEmpty()) {
                    _status.value = FabDBAPIStatus.NOTHING_FOUND
                }
                else {
                    _status.value = FabDBAPIStatus.DONE
                }

            } catch (e: Exception) {
                _status.value = FabDBAPIStatus.ERROR
                _cards.value = listOf()
            }
        }
    }
}
