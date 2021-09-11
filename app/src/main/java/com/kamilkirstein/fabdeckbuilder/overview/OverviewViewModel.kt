package com.kamilkirstein.fabdeckbuilder.overview


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkirstein.fabdeckbuilder.network.FabDbAPI
import com.kamilkirstein.fabdeckbuilder.network.Data
import kotlinx.coroutines.launch

enum class FabDBAPIStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private var _pageNumber : Int = 0
    private var _set : String? = null

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
    private fun getCards() {

        viewModelScope.launch {
            _status.value = FabDBAPIStatus.LOADING
            try {
                _cards.value = FabDbAPI.retrofitService.getCards().cardsData
                Log.i("response", _cards.value.toString())
                _status.value = FabDBAPIStatus.DONE
            } catch (e: Exception) {
                _status.value = FabDBAPIStatus.ERROR
                Log.i("response", "No Response")
                Log.e("Why no Response:", e.toString())
                _cards.value = listOf()
            }
        }
    }

    public fun getCardsForPage( page : Int){
        viewModelScope.launch {
            _status.value = FabDBAPIStatus.LOADING
            try {
                _cards.value = FabDbAPI.retrofitService.getCardsForPage(page).cardsData
                Log.i("response", _cards.value.toString())
                _status.value = FabDBAPIStatus.DONE
            } catch (e: Exception) {
                _status.value = FabDBAPIStatus.ERROR
                Log.i("response", "No Response")
                Log.e("Why no Response:", e.toString())
                _cards.value = listOf()
            }
        }
    }

    public fun getCardsOfSet( set : String?){
        viewModelScope.launch {
            _status.value = FabDBAPIStatus.LOADING
            try {
                _cards.value = FabDbAPI.retrofitService.getCardsOfSet(set).cardsData
                Log.i("response", _cards.value.toString())
                _status.value = FabDBAPIStatus.DONE
            } catch (e: Exception) {
                _status.value = FabDBAPIStatus.ERROR
                Log.i("response", "No Response")
                Log.e("Why no Response:", e.toString())
                _cards.value = listOf()
            }
        }
    }

    fun pageNumber():Int {return this._pageNumber
    }

    public fun nextPage(){
        ++_pageNumber
    }

    public fun prevPage(){
        if(_pageNumber == 0)
            return;
        --_pageNumber;

    }
}
