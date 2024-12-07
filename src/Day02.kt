import kotlin.math.abs

fun main() {
    fun checkLine(line: String): Boolean {
       val numbers = line.split(' ')

        var isAsc = numbers[0].toInt() < numbers[1].toInt()
        var isDesc = numbers[0].toInt() > numbers[1].toInt()

        for (idx in 0..numbers.size - 2) {
            var current = numbers[idx].toInt()
            var next = numbers[idx + 1].toInt()

            if (current == next) {
                return false
            }

            if (abs(current - next) > 3){
                return false
            }

            isAsc = current < next || isAsc
            isDesc = current > next || isDesc

            if (isAsc == true && isDesc == true) {
                return false
            }
        }

        return true
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            println("$line : Safe = ${checkLine(line)}")
            if(checkLine(line)) sum++
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
