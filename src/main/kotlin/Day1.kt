import utils.InputUtils

class Day1 {

    fun solvePart1(): Long {
        val input = InputUtils().readInput(1)
        val instructions = prepareInput(input)

        var code = 0L

        var currentPosition = 50L
        instructions.forEach { instruction ->
            if (instruction.direction == Direction.LEFT) {
                currentPosition -= instruction.value
                while (currentPosition < 0) {
                    currentPosition += 100
                }
            } else {
                currentPosition += instruction.value
                while (currentPosition > 99) {
                    currentPosition -= 100
                }
            }

            if (currentPosition == 0L) {
                code++
            }

            println("Instruction :$instruction brings us to: $currentPosition")

        }

        return code
    }

    fun solvePart2(): Long {
        val input = InputUtils().readInput(1)
        val instructions = prepareInput(input)

        var code = 0L

        var currentPosition = 50L
        instructions.forEach { instruction ->
            var pointingAt0 = 0
            val minusOne = currentPosition == 0L && instruction.direction == Direction.LEFT

            if (instruction.direction == Direction.LEFT) {
                currentPosition -= instruction.value
                while (currentPosition < 0) {
                    currentPosition += 100
                    pointingAt0++
                }
            } else {
                currentPosition += instruction.value
                while (currentPosition > 99) {
                    currentPosition -= 100
                    pointingAt0++
                }
            }

            if (instruction.direction == Direction.LEFT && currentPosition == 0L){
                pointingAt0++
            }

            if (minusOne) {
                pointingAt0--
            }

            if (pointingAt0 > 0) {
                println("$instruction brings us to: $currentPosition, pointing at 0 $pointingAt0 times")
            } else {
                println("$instruction brings us to: $currentPosition")
            }

            code += pointingAt0

        }

        return code
    }

}

private fun prepareInput(input: List<String>): ArrayList<Instruction> {
    val instructions: ArrayList<Instruction> = arrayListOf()

    input.forEach {
        val direction = Direction.fromValue(it.first().toString())
        val number = it.substring(1, it.length).toLong()
        instructions.add(Instruction(direction, number))
    }

    return instructions
}

data class Instruction(
    val direction: Direction,
    val value: Long
) {
    override fun toString(): String {
        return "${direction.value}$value"
    }
}

enum class Direction(var value: String) {
    LEFT("L"),
    RIGHT("R");

    companion object {
        fun fromValue(value: String): Direction =
            values().first { it.value == value }
    }

}