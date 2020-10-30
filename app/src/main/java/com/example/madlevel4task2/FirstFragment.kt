package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Game
import model.Hands
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment: Fragment() {
     var wins = 0
     var loss = 0
     var draw = 0
    private lateinit var navController: NavController
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var gamesRepository : GameRepository

    var listOfHands = listOf(
        Hands.ROCK,
        Hands.PAPER,
        Hands.SCISSOR
    )
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        rock.setOnClickListener(){
            move(Hands.ROCK)
            player.setImageResource(R.drawable.rock)

        }
        paper.setOnClickListener(){
            move(Hands.PAPER)
            player.setImageResource(R.drawable.paper)


        }
        scissor.setOnClickListener(){
            move(Hands.SCISSOR)
            player.setImageResource(R.drawable.scissors)

        }

        gamesRepository = GameRepository(requireContext())

        states()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_game, menu)
        val actionbar = (activity as AppCompatActivity).supportActionBar
        if (actionbar != null) {
            actionbar.setTitle(R.string.app_name)
            actionbar.setDisplayHomeAsUpEnabled(false)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_history -> {
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                Toast.makeText(context,"halo",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun move(playerMove: Hands){
        val cpuMove = listOfHands.random()
        val result = calculateWinner(playerMove , cpuMove)
        val game = Game(playerMove.getId(), cpuMove.getId(), Calendar.getInstance().time, result)
        saveGame(game)

    }

    fun updateStats(){
        textView3.text = getString(R.string.win_draw_lose, wins,draw,loss)
    }


    private fun saveGame(game: Game) {
        mainScope.launch {
                withContext(Dispatchers.IO){
                    gamesRepository.insertGame(game)
                }
            }
        return
        }

    private fun calculateWinner(playerMove: Hands, cpuMove: Hands): String {
        if ((playerMove == Hands.ROCK && cpuMove == Hands.SCISSOR)
            || playerMove == Hands.SCISSOR && cpuMove == Hands.PAPER ||
                playerMove == Hands.PAPER && cpuMove == Hands.ROCK) {
            Toast.makeText(context, "wwinner", Toast.LENGTH_LONG).show()
            wins++
            updateStats()
            return "You Win"
        }else if ( playerMove == cpuMove){
            Toast.makeText(context, "draw", Toast.LENGTH_SHORT).show()
            draw++
            updateStats()
            return "Draw"
        }else{
            Toast.makeText(context, "Loss", Toast.LENGTH_SHORT).show()
            loss++
            updateStats()
            return "Computer wins"
        }
    }

    private fun states(){
        mainScope.launch {
            withContext(Dispatchers.Main){
                for (game in gamesRepository.getAllGames()){
                    when(game.result){
                        "You Win"->{
                            wins++
                        }
                        "Draw" -> {
                            draw++
                        }
                        "Computer wins" -> {
                            loss++
                        }
                    }
                }
                updateStats()
            }
        }
    }

    public fun setStatesOnZero(){
        wins=0
        loss=0
        draw=0

    }

}


