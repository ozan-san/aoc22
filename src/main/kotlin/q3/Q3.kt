package q3

import java.io.File

fun main() {
    val f = File("src/main/kotlin/q3/q3.txt").inputStream().bufferedReader().readText()

    val s = f.split('\n').map {
        val firstCompartment = it.substring(0, it.length/2)
        val secondCompartment = it.substring(it.length/2, it.length)
        val firstElems = mutableSetOf<Char>()
        val secondElems = mutableSetOf<Char>()
        for (c in firstCompartment) {
            firstElems.add(c)
        }
        for (c in secondCompartment) {
            secondElems.add(c)
        }
        val common = firstElems.intersect(secondElems).first()
        if (common.isUpperCase()) {
            (common - 'A') + 27
        } else {
            (common - 'a') + 1
        }
    }.sum()

    println("Q1: ${s}")

    val lines = f.split('\n')
    var sum = 0
    for (i in 0 until (lines.size/3)) {
        val line1Items = lines[i * 3].toCharArray().toList().toSet()
        val line2Items = lines[i * 3 + 1].toCharArray().toList().toSet()
        val line3Items = lines[i * 3 + 2].toCharArray().toList().toSet()

        val common = line1Items.intersect(line2Items).intersect(line3Items).first()
        if (common.isUpperCase()) {
            sum += (common - 'A') + 27
        } else {
            sum += (common - 'a') + 1
        }
    }
    println("Q2: $sum")
}