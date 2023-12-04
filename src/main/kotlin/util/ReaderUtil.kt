package util

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ReaderUtil {
    companion object {
        fun readLinesFromFile(filename: String): List<String> {
            val file = File(filename)
            val reader = BufferedReader(FileReader(file))
            return reader.readLines()
        }
    }
}
