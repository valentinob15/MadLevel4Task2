package com.example.madlevel4task2
import androidx.room.*
import model.Game

// Data access object for the database table
@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("DELETE  FROM gameTable")
    suspend fun deleteAllGames()
}