
fun main() {
    fun findStart(
        input: List<String>,
    ): Pair<Pair<Int, Int>, Direction> {
        val width = input[0].length - 1
        val height = input.size - 1
        
        lateinit var origin: Pair<Int, Int>
        lateinit var direction: Direction
        for (row in 0..height) {
            for (col in 0..width) {
                var letter = input[row][col]

                when (letter) {
                    '^' -> {
                        origin = Pair(row, col)
                        direction = Direction.N
                    }

                    '>' -> {
                        origin = Pair(row, col)
                        direction = Direction.E
                    }

                    '<' -> {
                        origin = Pair(row, col)
                        direction = Direction.W
                    }

                    'v' -> {
                        origin = Pair(row, col)
                        direction = Direction.S
                    }
                }
            }
        }
        return Pair(origin, direction)
    }

    fun isInBounds(input: List<String>, coord: Pair<Int, Int>): Boolean {
        val (row, col) = coord

        if (row <= -1 || row >= input.size) {
            return false
        }

        if (col <= -1 || row >= input[0].length) {
            return false
        }

        return true
    }

    fun getNextDirection(direction: Direction): Direction {
        return when(direction) {
            Direction.N -> Direction.E
            Direction.E -> Direction.S
            Direction.S -> Direction.W
            Direction.W -> Direction.N
            else -> throw IllegalArgumentException()
        }
    }

    fun addCoords(first: Pair<Int,Int>, second: Pair<Int, Int>): Pair<Int, Int>{
        return Pair(first.first + second.first, first.second + second.second)
    }

    fun part1(input: List<String>): Int {
        var (origin, direction) = findStart(input)

        var paths = mutableSetOf<Pair<Int, Int>>()

        var coords = origin
        paths.add(coords)

        while(isInBounds(input, coords)){
            var nextCoords = addCoords(coords, direction.relativeCoords)

            if (!isInBounds(input, nextCoords)) break

            if(input[nextCoords.first][nextCoords.second] == '#') {
                direction = getNextDirection(direction)
                nextCoords = addCoords(coords, direction.relativeCoords)
            }

            coords = nextCoords
            paths.add(coords)
        }

        return paths.size
    }

    fun part2(input: List<String>): Int {

        return input.size
    }

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
