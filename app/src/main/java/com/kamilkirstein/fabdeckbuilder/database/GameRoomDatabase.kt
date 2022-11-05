package com.kamilkirstein.fabdeckbuilder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Database class with a singleton INSTANCE object.
 */
@Database(entities = [Game::class], version = 1, exportSchema =  false)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao() : GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context : Context): GameRoomDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "game_database"
                )
                // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}