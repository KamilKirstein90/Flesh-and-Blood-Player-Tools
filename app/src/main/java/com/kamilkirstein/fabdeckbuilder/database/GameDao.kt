package com.kamilkirstein.fabdeckbuilder.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game ORDER BY id ASC")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGame(id: Int): Flow<Game>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Game into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Update
    suspend fun update(game: Game)

    @Delete
    suspend fun delete(game: Game)
}