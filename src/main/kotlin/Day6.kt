import utils.InputUtils

class Day6 {

    val input = InputUtils().readLines(6)

    fun solvePart1(): Long {
        val problems = parseInput()
        return calculateProblems(problems)
    }

    fun solvePart2(): Long {
        val problems = parseInputAgain()
        return calculateProblems(problems)
    }

    private fun calculateProblems(problem: ArrayList<Pair<Operator, List<Long>>>): Long {
        var result = 0L
        problem.forEach { entry ->
            val operator = entry.first
            val sumOrProduct = when (operator) {
                Operator.SUM -> entry.second.sum()
                Operator.PRODUCT -> entry.second.product()
            }

            result += sumOrProduct
        }
        return result
    }

    private fun parseInput(): ArrayList<Pair<Operator, List<Long>>> {
        val horizontalNumbers = arrayListOf<List<Long>>()
        val operators = arrayListOf<String>()
        input.forEach { line ->
            if (line.first() == '*' || line.first() == '+') {
                val split = line.split(" ").filter { it != "" }
                operators.addAll(split)
                return@forEach
            }

            val numbers = line.split(" ").filter { it != "" }.map { it.toLong() }
            horizontalNumbers.add(numbers)
        }

        val verticalNumbers = horizontalNumbers.transpose()
        return transformNumbers(verticalNumbers, operators)
    }

    private fun transformNumbers(
        verticalNumbers: List<List<Long>>,
        operators: List<String>
    ): ArrayList<Pair<Operator, List<Long>>> =
        verticalNumbers.mapIndexed { index, list ->
            val op = if (operators[index] == "+") Operator.SUM else Operator.PRODUCT
            op to list
        }.toCollection(ArrayList())

    private fun parseInputAgain(): ArrayList<Pair<Operator, List<Long>>> {
        val horizontalNumbers = arrayListOf<ArrayList<String>>()
        val operators = arrayListOf<String>()
        input.forEach { line ->
            if (line.first() == '*' || line.first() == '+') {
                val split = line.split(" ").filter { it != "" }
                operators.addAll(split)
                return@forEach
            }

            val chars = line.toList().map { it.toString() }.toCollection(ArrayList())
            horizontalNumbers.add(chars)
        }
        val verticalNumberStrings = horizontalNumbers.transpose()

        val verticalNumbers = arrayListOf<ArrayList<Long>>()
        var problems = arrayListOf<Long>()

        verticalNumberStrings.forEach { numberString ->
            val currentNumber = numberString.joinToString("").trim()
            if (currentNumber.isEmpty()) {
                verticalNumbers.add(problems)
                problems = arrayListOf()
            } else {
                problems.add(currentNumber.toLong())
            }
        }
        verticalNumbers.add(problems)

        return transformNumbers(verticalNumbers, operators)
    }

}

enum class Operator {
    SUM, PRODUCT;
}

fun Iterable<Long>.product() = fold(1L) { acc, n -> acc * n }

fun <T> List<List<T>>.transpose(): List<List<T>> {
    if (isEmpty()) return emptyList()

    val maxCols = this.maxOf { it.size }

    return (0 until maxCols).map { col ->
        this.map { row ->
            row.getOrElse(col) { " " as T }
        }
    }
}


