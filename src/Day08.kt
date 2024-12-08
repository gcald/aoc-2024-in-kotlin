fun main() {
    fun createAntennaMap(input: List<String>): MutableMap<Char, Set<Pair<Int, Int>>> {
        var charMap = mutableMapOf<Char, Set<Pair<Int, Int>>>()

        val width = input[0].length - 1
        val height = input.size - 1

        for (row in 0..height) {
            for (col in 0..width) {
                var letter = input[row][col]

                if (!letter.isLetterOrDigit()) continue

                val antennas = charMap.getOrPut(letter) { emptySet() }

                val coord = Pair(row, col)

                charMap[letter] = antennas.plus(coord)
            }
        }
        return charMap
    }

    fun generateAntinodes(start: Pair<Int, Int>, end: Pair<Int, Int>): MutableSet<Pair<Int, Int>> {
        var antinodes = mutableSetOf<Pair<Int, Int>>()

        // [ROW/Y, COL/X]
        // Row, Col
        val rise = end.first - start.first
        val run = end.second - start.second

        antinodes.add(Pair(start.first - rise, start.second - run))
        antinodes.add(Pair(end.first + rise, end.second + run))

        return antinodes
    }

    fun isInBounds(bounds: Pair<Int, Int>, coords: Pair<Int, Int>): Boolean {
        val (row, col) = coords
        val (height, width) = bounds

        val inRowRange = 0 <= row && row <= height
        val inColRange = 0 <= col && col <= width

        return inRowRange && inColRange
    }

    fun generateMultipleAntinodes(bounds: Pair<Int, Int>, start: Pair<Int, Int>, end: Pair<Int, Int>): MutableSet<Pair<Int, Int>> {
        var antinodes = mutableSetOf<Pair<Int, Int>>()

        // [ROW/Y, COL/X]
        // Row, Col
        val rise = end.first - start.first
        val run = end.second - start.second

        var coords = start
        while(isInBounds(bounds, coords)){
            antinodes.add(coords)
            coords = Pair(coords.first - rise, coords.second - run)
        }
        
        //Split into while loops
        coords = end
        while(isInBounds(bounds, coords)) {
            antinodes.add(coords)
            coords = Pair(coords.first + rise, coords.second + run)
        }

        return antinodes
    }

    fun part1(input: List<String>): Int {
        var charMap = createAntennaMap(input)

        var antinodes = mutableSetOf<Pair<Int, Int>>()

        // Can probably be optimized since we're revisiting antennas that have already been added
        for (antennasEntry in charMap) {
            val antennas = antennasEntry.value
            for (antenna in antennas) {
                val otherAntennas = antennas.minus(antenna)
                for (other in otherAntennas) {
                    antinodes += generateAntinodes(antenna, other)
                }
            }
        }

        val height = input.size - 1
        val width = input[0].length - 1

        val filtered = antinodes.filter { (row, col) ->
            val inRowRange = 0 <= row && row <= height
            val inColRange = 0 <= col && col <= width

            inRowRange && inColRange
        }

        return filtered.size
    }

    fun part2(input: List<String>): Int {
        var charMap = createAntennaMap(input)
        var bounds = Pair(input.size - 1, input[0].length - 1)

        var antinodes = mutableSetOf<Pair<Int, Int>>()

        for (antennasEntry in charMap) {
            val antennas = antennasEntry.value
            for (antenna in antennas) {
                val otherAntennas = antennas.minus(antenna)
                for (other in otherAntennas) {
                    antinodes += generateMultipleAntinodes(bounds, antenna, other)
                    antinodes += generateAntinodes(antenna, other)
                }
            }
        }

        val height = input.size - 1
        val width = input[0].length - 1

        val filtered = antinodes.filter { (row, col) ->
            val inRowRange = 0 <= row && row <= height
            val inColRange = 0 <= col && col <= width

            inRowRange && inColRange
        }

        return filtered.size
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
