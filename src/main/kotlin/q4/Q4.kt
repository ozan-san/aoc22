package q4

import java.io.File

fun main() {
    val f = File("src/main/kotlin/q4/q4.txt").inputStream().bufferedReader().readText()
    val rangePairs = f.split('\n').map { it.split(',').map { it.split('-').map { it.toInt() } } }
    println(rangePairs.sumOf { pair ->
        val (a, b) = pair[0]
        val (x, y) = pair[1]
        if (a <= x && b >= y || x <= a && y >= b) 1L
        else 0L
    })

    println(rangePairs.sumOf { pair ->
        val (a, b) = pair[0]
        val (x, y) = pair[1]
        if ((a in x..y) || (b in x..y) || x in (a..b) || y in (a..b)) {
            1L
        } else 0L
    })
}