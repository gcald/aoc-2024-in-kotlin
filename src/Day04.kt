
fun main() {

    fun printMatrix(input: List<String>, coords: Pair<Int, Int>, matrixSize: Int): List<String> {

        var matrix = mutableListOf<String>()

        val (x, y) = coords

        for (row in x-matrixSize .. x+matrixSize) {
            if (row < 0 || row > input.size - 1) {
                println("..........")
                matrix.add("..........")
                continue
            }
            var line = ""
            for (col in y-matrixSize .. y+matrixSize) {
                if (col < 0 || col > input.size - 1) {
                    print('.')
                    line += '.'
                    continue
                }
                if(row == x && col == y) { print("*")} else {
                    print(input[row][col])
                }
                line += input[row][col]
            }
            println()
            matrix.add(line)
        }

        return matrix
    }

    fun addCoords(first: Pair<Int,Int>, second: Pair<Int, Int>): Pair<Int, Int>{
        return Pair(first.first + second.first, first.second + second.second)
    }


    fun part1(input: List<String>): Int {
        val width = input[0].length - 1
        val height = input.size - 1

        val possibleSearches = mutableListOf<List<String>>()

        for (row in 0 .. height){
            for (col in 0 .. width) {
                var letter = input[row][col]

                if (letter == 'X') {
                    possibleSearches.addFirst(printMatrix(input, Pair(row, col), 4))
                }
            }
        }

//        print(possibleSearches)

        var sum = 0

        println("SEARCHING MATRICES")
        possibleSearches.forEach { matrix ->
//            println("Searching Matrix:")
            val origin = Pair(4, 4)
//            printMatrix(matrix, origin)
            val search = "XMAS"
            // ROWS ARE Y AND COLS ARE X. DIRECTIONS ARE WRONG

            direction@for (direction in Direction.entries) {
//                println("DIRECTION: ${direction}")
                var searchIdx = 1

                var searchLetter = search[searchIdx]

                var coords = addCoords(origin, direction.relativeCoords)

                var letter = matrix[coords.first][coords.second]
//                println("(${coords.first},${coords.second}), ${letter}, $searchLetter")
//                println(letter)

                while(letter == searchLetter && searchIdx != search.length - 1){
//                    println("(${coords.first},${coords.second}), ${letter}, $searchLetter")

                    searchIdx++
                    coords = addCoords(coords, direction.relativeCoords)
                    // [ROW/Y, COL/X]
                    letter = matrix[coords.first][coords.second]
                    searchLetter = search[searchIdx]
                    println("(${coords.first},${coords.second}), $letter, $searchLetter")
                }

                if (searchIdx == search.length - 1 && letter == search[searchIdx]) {
                    sum++
//                    println("ADDING SUM: $sum")
                }
            }
        }

//        println("SUM: ${sum}")


        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
