package com.kamilkirstein.fabdeckbuilder.datafilter.enums

enum class Pitch (val pitch : String) {

    PITCH_1("1"),
    PITCH_2("2"),
    PITCH_3("3");

    override fun toString(): String {
        return pitch
    }


}