package q4

import java.io.File

fun main() {
    val f = File("src/main/kotlin/q4/q4.txt").inputStream().bufferedReader().readText()
    val rangePairs = f.split('\n').map { it.split(',').map { it.split('-').map { it.toInt() } } }
    println(rangePairs.map { pair ->
        val firstRange = pair[0]
        val secondRange = pair[1]
        val (a, b) = firstRange
        val (x, y) = secondRange
        if (a <= x && b >= y || x <= a && y >= b) 1
        else 0
    }.sum())

    println(rangePairs.map {pair ->
        val firstRange = pair[0]
        val secondRange = pair[1]
        val (a, b) = firstRange
        val (x, y) = secondRange
        if ((a in x..y) || (b in x..y) || x in (a..b) || y in (a..b)) {
            1
        } else 0
    }.sum())
}