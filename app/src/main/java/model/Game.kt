package model;



// Database table object model

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.*

@Entity(tableName = "gameTable")
data class Game (
        @ColumnInfo(name = "player_hand")
        var playerHand: Int,

        @ColumnInfo(name = "answer_pc")
        var cpuHand: Int,

        @ColumnInfo(name = "created_on")
        var createdOn: Date,

        @ColumnInfo(name = "result")
        var result: String,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null,
) {
        override fun toString(): String {
                return "Game(id=$id, answerUser=$playerHand, answerPc=$cpuHand, createdOn=$createdOn, result=$result)"
        }
}