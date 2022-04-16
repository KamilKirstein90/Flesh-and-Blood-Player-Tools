package com.kamilkirstein.fabdeckbuilder.ui.game

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.BindingAdapter
import com.kamilkirstein.fabdeckbuilder.R


class GameViewModel : ViewModel() {

    private var _gameMode = MutableLiveData<String>()
    val gameMode : LiveData<String>
        get() = _gameMode



    private val _lifePointsOpponent = MutableLiveData<Int>(40)
    val lifePointsOpponent : LiveData<Int>
        get() = _lifePointsOpponent

    private val _lifePointsPlayer = MutableLiveData<Int> (40)
    val lifePointsPlayer : LiveData<Int>
        get() = _lifePointsPlayer

    private val _imgResOpponent = MutableLiveData<Int> (R.drawable.hero_adult_oldhim)
    val imgResOpponent : LiveData<Int>
        get() = _imgResOpponent

    private val _imgResPlayer = MutableLiveData<Int> (R.drawable.hero_adult_visserai)
    val imgResPlayer : LiveData<Int>
        get() = _imgResPlayer

    fun addLifePointsOpponent()
    {
        _lifePointsOpponent.value = _lifePointsOpponent.value?.plus(1)
    }

    fun subLifePointsOpponent()
    {
        _lifePointsOpponent.value = _lifePointsOpponent.value?.minus(1)
    }

    fun addLifePointsPlayer ()
    {
        _lifePointsPlayer.value = _lifePointsPlayer.value?.plus(1)
    }

    fun subLifePointsPlayer()
    {
        _lifePointsPlayer.value = _lifePointsPlayer.value?.minus(1)
    }

    fun setGameMode(gameMode :String)
    {
        _gameMode.value = gameMode
    }

    @BindingAdapter("customSetSrc:")
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }


}