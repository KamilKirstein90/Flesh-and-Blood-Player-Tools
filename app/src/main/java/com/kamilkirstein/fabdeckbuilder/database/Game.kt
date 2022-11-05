package com.kamilkirstein.fabdeckbuilder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.time.LocalDate


@Entity
data class Game(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "gameMode")
    val gameMode: Int,
    @ColumnInfo(name = "opponentsHero")
    val opponentsHero: String,
    @ColumnInfo(name = "playerHero")
    val playerHero: String,
    @ColumnInfo(name = "result")
    val result : Boolean,
 )


