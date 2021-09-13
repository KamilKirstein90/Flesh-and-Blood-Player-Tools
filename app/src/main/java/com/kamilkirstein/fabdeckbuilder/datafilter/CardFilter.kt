package com.kamilkirstein.fabdeckbuilder.datafilter

import android.util.Log

public class CardFilter {

    private
    var pageNumber : Integer? = null
    var m_name : String? = null
    var m_keyWords : MutableSet<KeyWords> = mutableSetOf() // set because no duplicate elements are allowed
    var m_keyWordsString : MutableSet<String>? = mutableSetOf()// only constants allowed from above
    var m_pitch : String? = null // only 1, 2, 3 allowed // enum pitch
    var m_cost : String? = null // only 0, 1, 2, 3, 4+ // enum cost
    var m_rarity: String? = null // only C, R, S, T, L, F, P // enum rarity
    var m_set : String? = null // only WTR, ARC, CRU, MON // enum set

    public fun getKeyWordsStringFromKeyWords():MutableSet<String>?
    {
        m_keyWordsString?.clear()
        if (m_keyWords.contains(KeyWords.KEYWORDS_ALL))
            return null
        // create value for key keywords like : ["mechanologist","action","item"]
        for (e in m_keyWords)
        {
            m_keyWordsString?.add(e.toString())
        }
        return m_keyWordsString
    }
}
