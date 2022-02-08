package com.kamilkirstein.fabdeckbuilder.datafilter.enums

enum class Set(val set :String)
{
    SET_ALL ("ALL"),
    SET_WELCOME_TO_RATHE ("WTR"),
    SET_ARCANE_RISING("ARC"),
    SET_MONARCH("MON"),
    SET_CRUSIBLE_OF_WAR("CRU"),
    SET_TALES_OF_ARIA("ELE"),
    SET_EVERFEST("EVR");

    override fun toString(): String {
        return set;
    }

}