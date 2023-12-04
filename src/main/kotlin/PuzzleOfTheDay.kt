abstract class PuzzleOfTheDay(
    private val day: String,
) {
    val inputFolder = "input/$day/"
    val partOne = "$inputFolder/a.txt"
    val partOneTest = "$inputFolder/a_test.txt"
    val partTwo = "$inputFolder/b.txt"
    val partTwoTest = "$inputFolder/b_test.txt"

    abstract fun parseLine(line: String): Any
    abstract fun runPartOne(lines: List<String>): Any
    abstract fun runPartTwo(lines: List<String>): Any
}
