package com.kamilkirstein.fabdeckbuilder.datafilter

enum class Cost (val cost : String)
{
    COST_EMPTY(""),
    COST_0 ("0"),
    COST_1("1"),
    COST_2("2"),
    COST_3("3"),
    COST_4_OR_GREATER( "4+");

    override fun toString(): String {
        return cost;
    }


}