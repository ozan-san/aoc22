package q1

import java.io.File

fun main(args: Array<String>) {
    val f = File("src/main/kotlin/q1/q1.txt").inputStream().bufferedReader().readText()
    val groupSums = f.split("\n\n").map { partition -> partition.split('\n').map { it.toInt() } }.map { it.sum() }

    // region Q1
    val maxIndex = groupSums.indices.maxBy { groupSums[it] }
    println("Q1: ${groupSums[maxIndex]}")
    // endregion

    // region Q2
    val maxThree = groupSums.fold(mutableListOf<Int>()) {
        candidates, element ->
            if (candidates.size < 3) {
                candidates.add(element)
                candidates
            } else {
                if (candidates.any { candidateElement -> candidateElement < element }) {
                    candidates.add(element)
                    candidates.sort()

                    if (candidates.size > 3) {
                        candidates.drop(1).toMutableList()
                    } else {
                        candidates
                    }
                } else candidates
            }
    }
    println("Q2: ${maxThree.sum()}")
    // endregion
}