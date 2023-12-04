package `04`

import PuzzleOfTheDay
import util.ReaderUtil
import java.lang.Integer.parseInt
import kotlin.math.pow

data class ScratchCard(
    val id: Int = 0,
    val winningNumbers: List<Int>,
    val gameNumbers: List<Int>,
) {
    private val intersection: List<Int> = winningNumbers.intersect(gameNumbers).toList()
    val numberOfWins: Int = intersection.size

    fun calculatePoints(): Int {
        return if (numberOfWins == 0) {
            0
        } else {
            2f.pow(numberOfWins - 1).toInt()
        }
    }
}

fun main() {
    val lines = ReaderUtil.readLinesFromFile("input/04/b.txt")
    val cards = lines.map { parseLine(it) }.toMutableList()

    for (i: Int in 1..cards.size) {
        val cardsForNumber = cards.getCardsWithNumber(i)
        cardsForNumber.forEach {
            val wins = it.numberOfWins
            val cardNumberRange = i + 1..i + wins
            for (j: Int in cardNumberRange) {
                val cardCopy = cards.getSingleCard(j).copy()
                cards.add(cardCopy)
            }
        }
    }

    print(cards.size)
}

fun main_A() {
    val lines = ReaderUtil.readLinesFromFile("input/04/a.txt")
    val cards = lines.map { parseLine(it) }
    val pointsPerCard = cards.map {
        it.calculatePoints()
    }

    println(pointsPerCard.sum())
}

fun List<ScratchCard>.getCardsWithNumber(number: Int): List<ScratchCard> {
    return this.filter { it.id == number }
}

fun List<ScratchCard>.getSingleCard(number: Int): ScratchCard {
    return this.first { it.id == number }
}

fun parseLine(line: String): ScratchCard {
    val numbers = line.split(":")[1]
    val cardNumber = parseInt(line.split(":")[0].split(" ").filter { it.isNotEmpty() }[1])
    val winningNumbersString = numbers.split("|")[0]
    val gameNumbersString = numbers.split("|")[1]

    return ScratchCard(
        id = cardNumber,
        winningNumbers = parseNumbersStringToList(winningNumbersString),
        gameNumbers = parseNumbersStringToList(gameNumbersString),
    )
}

fun parseNumbersStringToList(numbersString: String): List<Int> {
    val tokens = numbersString.trim().split(" ").filter { it.isNotEmpty() }
    return tokens.map { parseInt(it) }
}
