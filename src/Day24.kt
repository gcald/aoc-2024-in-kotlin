class Operation(val operators: Pair<String, String>, val operand: String, val result: String) {
    fun evaluate(operators: Pair<Boolean, Boolean>): Boolean {
        return when(operand) {
            "AND" -> {
                operators.first.and(operators.second)
            }

            "OR" -> {
                operators.first.or(operators.second)
            }

            "XOR" -> {
                operators.first.xor(operators.second)
            }

            else -> throw Exception("No matching operand")
        }
    }

    override fun toString(): String {
        return "${operators.first} $operand ${operators.second} -> $result"
    }
}


fun main() {
    fun part1(input: List<String>): Any {
        val lineBreak = input.indexOf("")
        val initialStates = input.subList(0, lineBreak)
        val logicGates = input.subList(lineBreak + 1, input.lastIndex + 1)

        val initialStatesMap = mutableMapOf<String, Boolean>()
        initialStates.forEach { state ->
            var (wire, value) = state.split(": ")

            initialStatesMap.put(wire, value == "1")
        }

        initialStatesMap.forEach { t, u -> println("$t : $u") }
        println()

        val operationsList = mutableListOf<Operation>()
        logicGates.forEach { gate ->
            val logicGate = gate.split(" ").filter { str -> str != "->" }
            val operators = Pair(logicGate[0], logicGate[2])
            val operation = Operation(operators, logicGate[1], logicGate[3])

            operationsList.add(operation)
        }

        println("Size: ${operationsList.size}")

        while (operationsList.isNotEmpty()) {

            val operationsIterator = operationsList.iterator()

//            println(operationsIterator.hasNext())

            while (operationsIterator.hasNext()) {
//                print("IN")
                val operation = operationsIterator.next()
                println(operation)
                val first: Boolean? = initialStatesMap.get(operation.operators.first)
                val second: Boolean? = initialStatesMap.get(operation.operators.second)

                if (first == null || second == null) continue

                println("$operation = ${operation.evaluate(Pair(first, second))}")

                initialStatesMap.put(operation.result, operation.evaluate(Pair(first, second)))
                operationsIterator.remove()

            }
        }


        println("DONE")
        println("Size: ${operationsList.size}")
        initialStatesMap.forEach { t, u -> println("$t : $u") }

        var zlist = initialStatesMap.filter({entry -> entry.key.contains('z') }).toSortedMap()


        var string = ""
        zlist.forEach { key, value ->
            val toInt = value.compareTo(false)
            string += toInt
        }

        val toInt = string.reversed().toBigInteger(2)

        return toInt
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day24")
    part1(input).println()
    part2(input).println()
}
