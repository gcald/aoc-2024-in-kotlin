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
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        input.forEach { line ->
            val entries = line.split("   ")
            list1.add(entries[0].toInt())
            list2.add(entries[1].toInt())
        }

        list1.sort()
        list2.sort()

        val numMap = mutableMapOf<Int, Int>()

        list1.forEach { number ->
            if (numMap.containsKey(number)) {
                return@forEach
            }

            numMap.put(number, 0)
        }

        list2.forEach { number ->
            if (!numMap.containsKey(number)) {
                return@forEach
            }

            var sum = numMap[number]

            if (sum === null) {
                return@forEach
            }

            numMap.put(number, sum + 1)
        }

        var sum = 0

        numMap.forEach { number, multiplier ->
            sum += number * multiplier
        }

        return sum
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
