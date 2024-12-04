
fun main() {


    fun findInstructions(
        input: List<String>,
        state: String
    ): MutableList<String> {
        var possibleStrings = mutableListOf<String>()

        var buffer = ""
        var idx = 0
        input.forEach { line ->
            line.forEach { char ->
                if (char == state[idx]) {
                    buffer += char

                    if (idx != state.length - 1) {
                        idx++
                        return@forEach
                    }

                    possibleStrings.add(buffer)
                }

                if (state[idx] == '*') {
                    var nextChar = state[idx]
                    var nextIdx = 0

                    while (nextChar == '*') {
                        nextIdx++
                        nextChar = state[idx + nextIdx]
                    }

                    when {
                        char.isDigit() -> {
                            buffer += char
                            idx++
                            return@forEach
                        }

                        char == nextChar -> {
                            buffer += char
                            idx += nextIdx

                            if (idx != state.length - 1) {
                                idx++
                                return@forEach
                            }

                            possibleStrings.add(buffer)
                        }
                    }
                }

                idx = 0
                buffer = ""
            }
        }
        return possibleStrings
    }

    fun findInstructionsMap(
        input: List<String>,
        state: String,
    ): MutableMap<Int, String> {
        var possibleStringsMap = mutableMapOf<Int, String>()

        var buffer = ""
        var totalIdx = 0
        var idx = 0
        input.forEach { line ->
            line.forEach { char ->
                totalIdx++
                if (char == state[idx]) {
                    buffer += char

                    if (idx != state.length - 1) {
                        idx++
                        return@forEach
                    }

                    possibleStringsMap.put(totalIdx, buffer)
                }

                if (state[idx] == '*') {
                    var nextChar = state[idx]
                    var nextIdx = 0

                    while (nextChar == '*') {
                        nextIdx++
                        nextChar = state[idx + nextIdx]
                    }

                    when {
                        char.isDigit() -> {
                            buffer += char
                            idx++
                            return@forEach
                        }

                        char == nextChar -> {
                            buffer += char
                            idx += nextIdx

                            if (idx != state.length - 1) {
                                idx++
                                return@forEach
                            }

                            possibleStringsMap.put(totalIdx, buffer)
                        }
                    }
                }

                idx = 0
                buffer = ""
            }
        }
        return possibleStringsMap
    }

    fun sumAndMultiply(possibleStrings: MutableList<String>): Int {
        var sum = 0

        possibleStrings.forEach { string: String ->
            var newString = string.replace("mul(", "")
            newString = newString.replace(")", "")
            var operands = newString.split(',')

            sum += operands[0].toInt() * operands[1].toInt()
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        var multiplication = findInstructions(input, "mul(***,***)")

        var sum = sumAndMultiply(multiplication)

        return sum
    }

    fun part2(input: List<String>): Int {
        var multiplication = findInstructionsMap(input, "mul(***,***)")
        var dos = findInstructionsMap(input, "do()")
        var donts = findInstructionsMap(input, "don't()")

        var merged = multiplication + dos + donts
        merged = merged.toSortedMap()

        var isEnabled = true
        var enabledMults = mutableListOf<String>()
        merged.forEach { idx, string ->
            when (string) {
                "don't()" -> {
                    isEnabled = false
                }
                "do()" -> {
                    isEnabled = true
                }
                else -> {
                    if (isEnabled) {
                        enabledMults.add(string)
                    }
                }
            }
        }

        return sumAndMultiply(enabledMults)
    }

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
