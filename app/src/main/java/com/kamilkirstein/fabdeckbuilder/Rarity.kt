package com.kamilkirstein.fabdeckbuilder

enum class Rarity(val rarity: String) {

    RARITY_COMMON("C"),
    RARITY_RARE("R"),
    RARITY_SUPER("S"),
    RARITY_TOKEN( "T"),
    RARITY_MAJESTIC("M"),
    RARITY_LEGENDARY("L"),
    RARITY_PROMO("P"),
    RARITY_FABLED("F");

    override fun toString(): String {
        return rarity;
    }

}