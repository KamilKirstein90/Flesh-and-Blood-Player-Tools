package com.kamilkirstein.fabdeckbuilder.datafilter.enums

enum class KeyWords(val keyWord : String) {

    KEYWORDS_ALL("all"),
    KEYWORDS_GENERIC("generic"),
    KEYWORD_ASSASIN("assasin"),
    KEYWORDS_BRUTE("brute"),
    KEYWORDS_GUARDIAN("guardian"),
    KEYWORDS_ILLUSIONIST("illusionist"),
    KEYWORDS_MECCHANOLOGIST("mechanologist"),
    KEYWORDS_NINJA("ninja"),
    KEYWORDS_RANGER("ranger"),
    KEYWORDS_RUNEBLADE("runeblade"),
    KEYWORDS_WARRIOR("warrior"),
    KEYWORDS_WIZARD("wizard"),
    KEYWORDS_LIGHT("light"),
    KEYWORDS_SHADOW("shadow"),
    KEYWORDS_ELEMENTAL("elemental"),
    KEYWORDS_EARTH("earth"),
    KEYWORDS_ICE("ice"),
    KEYWORDS_LIGHTNING("lightning"),
    KEYWORDS_ACTION("action"),
    KEYWORDS_ATTACK("attack"),
    KEYWORDS_DEFENSE("defense"),
    KEYWORDS_REACTION("reaction"),
    KEYWORDS_INSTANT("instant"),
    KEYWORDS_ITEM("item"),
    KEYWORDS_ARROW_ATTACK("arrow attack"),
    KEYWORDS_WEAPON("weapon"),
    KEYWORDS_AURA("aura");

    // now views like spinner will get the value of the keyword when I pass a enum list to them
    override fun toString(): String {
        return keyWord
    }}
