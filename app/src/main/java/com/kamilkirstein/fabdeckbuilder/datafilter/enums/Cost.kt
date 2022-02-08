package com.kamilkirstein.fabdeckbuilder.datafilter.enums

enum class Cost (val cost : String)
{
    COST_1("1"),
    COST_2("2"),
    COST_3("3"),
    COST_4_OR_GREATER( "4+");

    override fun toString(): String {
        return cost;
    }


}