import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Any {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        input.forEach { line ->
            val entries = line.split("   ")
            list1.add(entries[0].toInt())
            list2.add(entries[1].toInt())
        }

        list1.sort()
        list2.sort()

        var differences = 0

        for (i in list1.indices) {
            differences += abs(list1[i] - list2[i])
        }

        return differences
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
