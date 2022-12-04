package q2

import q2.Move.*
import q2.Outcome.*
import java.io.File

enum class Move(
    val oppMoveSymbol: String,
    val playerMoveSymbol: String,
    val playerMoveValue: Int
) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3)
}

enum class Outcome(
    val value: Int,
    val encoding: String
) {
    WIN(6, "Z"),
    DRAW(3, "Y"),
    LOSE(0, "X")
}

data class RoundStatus(
    val playerMove: Move,
    val outcome: Outcome
) {
    val roundValue: Int
        get() = playerMove.playerMoveValue + outcome.value
}

internal fun getRoundStatus(oppMove: Move, playerMove: Move): RoundStatus {
    return when (oppMove) {
        ROCK -> when (playerMove) {
            ROCK -> RoundStatus(playerMove, DRAW)
            PAPER -> RoundStatus(playerMove, WIN)
            SCISSORS -> RoundStatus(playerMove, LOSE)
        }

        PAPER -> when (playerMove) {
            ROCK -> RoundStatus(playerMove, LOSE)
            PAPER -> RoundStatus(playerMove, DRAW)
            SCISSORS -> RoundStatus(playerMove, WIN)
        }

        SCISSORS -> when (playerMove) {
            ROCK -> RoundStatus(playerMove, WIN)
            PAPER -> RoundStatus(playerMove, LOSE)
            SCISSORS -> RoundStatus(playerMove, DRAW)
        }
    }
}

internal fun getStrategisedMove(oppMove: Move, outcome: Outcome): RoundStatus {
    return when (oppMove) {
        ROCK -> when (outcome) {
            WIN -> RoundStatus(PAPER, outcome)
            DRAW -> RoundStatus(ROCK, outcome)
            LOSE -> RoundStatus(SCISSORS, outcome)
        }
        PAPER -> when (outcome) {
            WIN -> RoundStatus(SCISSORS, outcome)
            DRAW -> RoundStatus(PAPER, outcome)
            LOSE -> RoundStatus(ROCK, outcome)
        }
        SCISSORS -> when (outcome) {
            WIN -> RoundStatus(ROCK, outcome)
            DRAW -> RoundStatus(SCISSORS, outcome)
            LOSE -> RoundStatus(PAPER, outcome)
        }
    }
}

fun main(args: Array<String>) {
    val f = File("src/main/kotlin/q2/q2.txt").inputStream().bufferedReader().readText()

    // region Q1
    val rounds = f.split('\n').map { line ->
        val moves = line.split(' ')
        val oppMove = Move.values().first { it.oppMoveSymbol == moves[0] }
        val playerMove = Move.values().first { it.playerMoveSymbol == moves[1] }
        Pair(oppMove, playerMove)
    }


    println(
        "Q1: ${
            rounds.map {
                val (opp, player) = it
                getRoundStatus(opp, player)
            }.sumOf { it.roundValue }
        }"
    )
    // endregion

    // region Q2
    val rounds2 = f.split('\n').map {line ->
        val moveOutcomePair = line.split(' ')
        val oppMove = Move.values().first { it.oppMoveSymbol == moveOutcomePair[0] }
        val outcome = Outcome.values().first {it.encoding == moveOutcomePair[1]}
        getStrategisedMove(oppMove, outcome)
    }

    println("Q2: ${rounds2.sumOf { it.roundValue }}")
    // endregion
}