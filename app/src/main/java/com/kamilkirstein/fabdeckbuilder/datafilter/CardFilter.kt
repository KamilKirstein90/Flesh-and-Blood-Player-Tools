package com.kamilkirstein.fabdeckbuilder.datafilter

import android.util.Log
import com.kamilkirstein.fabdeckbuilder.Rarity
import com.kamilkirstein.fabdeckbuilder.datafilter.enums.Cost
import com.kamilkirstein.fabdeckbuilder.datafilter.enums.KeyWords
import com.kamilkirstein.fabdeckbuilder.datafilter.enums.Pitch

public  class CardFilter()
{
    var _pageNumber : Int = 1// we always start at page 1
    var _name : String? = null
    var _set : String? = null
    var _keyWords : MutableSet<KeyWords> = mutableSetOf() // set because no duplicate elements are allowed
    var _keyWordsString : MutableSet<String>? = mutableSetOf()// only constants allowed from above
    var _pitch : Pitch? = null // only 1, 2, 3 allowed // enum pitch
    var _cost : Cost? = null // only 0, 1, 2, 3, 4+ // enum cost
    var _rarity: Rarity? = null // only C, R, S, T, L, F, P // enum rarity

    // the next two values are values that are only used in the
    // keyWordString with the KEY_WORDS_ATTACK, KEY_WORDS_DEFFENSE  =<> to get cards with the given
    // attack and defense value
    var _defenseValue : Integer? = null
    var _attackValue : Integer? =  null

    // change the page
    public fun nextPage(){
        ++_pageNumber
    }

    public fun prevPage(){
        if(_pageNumber == 1)
            return;
        --_pageNumber;
    }

    public fun createKeyWordString()
    {
        _keyWordsString?.clear()

        if (_keyWords.contains(KeyWords.KEYWORDS_ALL))
            return;

        // create value for key keywords like : ["mechanologist","action","item"]
        for (e in _keyWords)
        {
            _keyWordsString?.add(e.toString())
        }
        Log.i("KeyWordString", _keyWordsString.toString())
        return;
    }
}
