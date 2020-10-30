package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_history_layout.view.*
import model.Game
import model.Hands

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {

            val cpuImage = Hands.returnImage(game.cpuHand)
            val playerImage = Hands.returnImage(game.playerHand)
            if (cpuImage != null) {
                itemView.tvDate.text = game.createdOn.toString()
                itemView.tvResult.text = game.result
                itemView.cpuHistory.setImageResource(cpuImage.getImage())
                if (playerImage != null) {
                    itemView.playerHistory.setImageResource(playerImage.getImage())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_history_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}