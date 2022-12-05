package com

import kotlin.random.Random
import kotlin.random.nextInt

class Dice {
    fun rollDice(): Int {
        return Random.nextInt(1..6)
    }
}

data class Player(val name: String, var movedDistance: Int) {

    fun movePosition(position: Int) {
        movedDistance += position
    }
}

class DiceGame(var playerCount: Int) {
    private var dice = Dice()
    private var players = mutableListOf<Player>()
    private var gameTarget = 100

    init {
        if (playerCount <= 6) {
            repeat(playerCount) {
                players.add(Player("Player ${it + 1 }", 0))
            }

        } else {
            TODO("The range is over only play 6 or 6 below")
        }
    }

    fun startGame() {
        var currentPlayerIndex = 0

        while (true) {
            val player = players[currentPlayerIndex]
            val diceValue = dice.rollDice()

            if (player.movedDistance + diceValue <= 100)
                player.movePosition(diceValue)

            if (player.movedDistance >= gameTarget) {
                break
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size
        }

        players.sortByDescending { it.movedDistance }
        players.forEach {
            println("${it.name}, Score: ${it.movedDistance}")
        }
    }
}

fun main() {
    val game = DiceGame(3)
    game.startGame()
}

