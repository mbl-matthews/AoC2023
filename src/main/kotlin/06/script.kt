package `06`

import util.ReaderUtil
import java.lang.Integer.parseInt
import java.lang.Long.parseLong
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val puzzle = Script()

    val start = System.currentTimeMillis()
    puzzle.runPartOne()
    puzzle.runPartTwo()
    val end = System.currentTimeMillis()
    println(end-start)
}

fun calcPQ(p: Double, q: Double): Pair<Double, Double> {
    val pSolve = -(p/2)
    val qSolve = sqrt(
        (p / 2).pow(2) - q,
    )

    return Pair(pSolve + qSolve, pSolve - qSolve)
}

class Race(
    val time: Long,
    val distance: Long,
) {
    private fun calculateLowerAndUpper(): Pair<Int, Int> {
        val pq = calcPQ(-time.toDouble(), (distance + 1).toDouble())
        val upperBound = floor(pq.first).toInt()
        val lowerBound = ceil(pq.second).toInt()

        return Pair(lowerBound, upperBound)
    }

    fun possibleWinSolutions(): Int {
        val bounds = calculateLowerAndUpper()
        val lowerBound = bounds.first
        val upperBound = bounds.second

        return upperBound - lowerBound + 1
    }
}

class Script {
    private fun readPuzzle(filename: String): List<Race> {
        val lines = ReaderUtil.readLinesFromFile(filename)
        val parsedLines = lines.map {
            it.split(" ").drop(1).filter { t ->
                t.isNotEmpty()
            }.map { t ->
                parseInt(t)
            }
        }

        val races = ArrayList<Race>()
        for (i in 0..<parsedLines[0].size) {
            races.add(
                Race(
                    time = parsedLines[0][i].toLong(),
                    distance = parsedLines[1][i].toLong(),
                ),
            )
        }
        return races
    }

    fun runPartOne() {
        val races = readPuzzle("input/06/a.txt")
        val possibleWins = races.map {
            it.possibleWinSolutions()
        }

        val attempts = possibleWins.reduce { acc, next -> acc * next }
        println(attempts)
    }

    fun runPartTwo() {
        val races = readPuzzle("input/06/a.txt")
        val modifiedRace = Race(
            parseLong(races.map { it.time }.joinToString(separator = "")),
            parseLong(races.map { it.distance }.joinToString(separator = "")),
        )

        val attempts = modifiedRace.possibleWinSolutions()
        println(attempts)
    }
}
