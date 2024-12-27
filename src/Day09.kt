import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.exp

fun main() {
//    fun expandDiskMap(index: Int, substring: ) {}

    fun expandDiskMap(diskMap: String): MutableList<String> {
        var expanded = mutableListOf<String>()
        var diskIndex = 0
        var isBlock = true

        for (i in 0..diskMap.length - 1) {
            var currentSize = diskMap[i].toString().toInt()
            var currentLine = mutableListOf<String>()
            var currentChar: String

            if (isBlock) {
                currentChar = diskIndex.toString()
                diskIndex++
            } else {
                currentChar = "."
            }

            (0..currentSize - 1).forEach { i ->
                currentLine += currentChar
            }

            expanded.addAll(currentLine)
            isBlock = !isBlock
        }

        return expanded
    }

    fun condenseDiskMap(expanded: MutableList<String>): MutableList<String> {
        var expanded = expanded
        var frontIdx = 0
        var backIdx = expanded.size - 1

        while (frontIdx < backIdx) {
            if (expanded[frontIdx] != ".") {
                frontIdx++
                continue
            }

            if (expanded[backIdx] == ".") {
                backIdx--
                continue
            }

            expanded[frontIdx] = expanded[backIdx]
            expanded[backIdx] = "."

            frontIdx++
            backIdx--
        }

        return expanded
    }

    fun calculateCheckSum(condensed: MutableList<String>): BigInteger {
        var diskIndex = 0
        var checkSum = BigInteger("0")

        val filtered = condensed.filter { string -> string != "." }
        filtered.forEach { position ->
            checkSum += position.toBigInteger() * diskIndex.toBigInteger()
            diskIndex++
        }

        return checkSum
    }

    fun part1(input: List<String>): Any {
        var diskMap = input[0]

        val expanded = expandDiskMap(diskMap)

        val condensed = condenseDiskMap(expanded)

        return calculateCheckSum(condensed)
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
