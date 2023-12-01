package `01` // ktlint-disable package-name

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Integer.parseInt

val numberStringMap = mapOf(
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9',
)
val numberStrings = numberStringMap.keys.toList()

fun main() {
    val file = File("input/01/b.txt")
    val reader = BufferedReader(FileReader(file))
    val lines = reader.readLines()

    val numbers = mutableListOf<Int>()
    for (line in lines) {
        val parsed = parseLine(line)
        numbers.add(parsed)
    }

    val sumA = numbers.sum()
    var sumB = 0
    numbers.forEach {
        sumB += it
    }

    if (sumA != sumB) {
        println("WTF")
    }

    println(sumB)
}

fun parseLine(line: String): Int {
    val firstNumString = getFirstNumberStringWithIndexOrNull(line)
    val firstNumDigitChar = line.firstOrNull { c -> c.isDigit() } ?: '_'
    val firstNumDigit = Pair(line.indexOf(firstNumDigitChar), firstNumDigitChar)
    val first = if (firstNumString == null) {
        firstNumDigit
    } else if (firstNumDigitChar == '_') {
        firstNumString
    } else {
        listOf(firstNumDigit, firstNumString).minBy { p -> p.first }
    }

    val lastNumString = getLastNumberStringWithIndexOrNull(line)
    val lastNumDigitChar = line.lastOrNull { c -> c.isDigit() } ?: '_'
    val lastNumDigit = Pair(line.lastIndexOf(lastNumDigitChar), lastNumDigitChar)
    val last = if (lastNumString == null) {
        lastNumDigit
    } else if (lastNumDigitChar == '_') {
        lastNumString
    } else {
        listOf(lastNumDigit, lastNumString).maxBy {
                p -> p.first
        }
    }

    return if (first.first == last.first) {
        parseInt("${first.second}")
    } else {
        parseInt("${first.second}${last.second}")
    }
}

fun getDigitCharForNumStringOrNull(value: String?): Char {
    return numberStringMap[value]!!
}

fun getFoundNumberStrings(value: String): List<Pair<Int, Char>> {
    val firstIndices = numberStrings.map {
        Pair(value.indexOf(it), getDigitCharForNumStringOrNull(it))
    }
    val lastIndices = numberStrings.map {
        Pair(value.lastIndexOf(it), getDigitCharForNumStringOrNull(it))
    }

    val combined = firstIndices + lastIndices
    return combined.filter {
        it.first != -1
    }
}

fun getFirstNumberStringWithIndexOrNull(value: String): Pair<Int, Char>? {
    val foundNums = getFoundNumberStrings(value)

    return if (foundNums.isEmpty()) {
        null
    } else {
        foundNums.minBy { it.first }
    }
}

fun getLastNumberStringWithIndexOrNull(value: String): Pair<Int, Char>? {
    val foundNums = getFoundNumberStrings(value)

    return if (foundNums.isEmpty()) {
        null
    } else {
        foundNums.maxBy { it.first }
    }
}

fun main_one() {
    val file = File("input/01/a.txt")
    val reader = BufferedReader(FileReader(file))

    val lines = reader.readLines()
    val numbers = lines.map {
        val first = it.first { c -> c.isDigit() }
        val last = it.last { c -> c.isDigit() }

        parseInt("$first$last")
    }
    println(numbers.sum())
}
