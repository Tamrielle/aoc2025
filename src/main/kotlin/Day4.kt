import utils.InputUtils

class Day4 {

    val input = InputUtils().readLines(4)

    fun solvePart1(): Int {
        return getAccessibleRollPositions(input).size
    }

    private fun getAccessibleRollPositions(rollSetup: List<String>): ArrayList<Pair<Int, Int>> {
        val positions = arrayListOf<Pair<Int, Int>>()

        for (y in rollSetup.indices) {
            for (x in rollSetup[y].indices) {
                val position = rollSetup[y][x]
                if (position == '.') {
                    continue
                }
                val adjacentRolls = getAdjacent(rollSetup, x, y)
                if (adjacentRolls.count { it == '@' } < 4) {
                    positions.add(Pair(x, y))
                }

                // println("($x,$y) '$position': $adjacentRolls")
            }
        }

        return positions
    }

    fun getAdjacent(grid: List<String>, x: Int, y: Int): List<Char> {
        val adjacentIndices = listOf(
            -1 to -1, 0 to -1, 1 to -1,
            -1 to 0, 1 to 0,
            -1 to 1, 0 to 1, 1 to 1
        )

        val result = mutableListOf<Char>()

        for ((posX, posY) in adjacentIndices) {
            val nx = x + posX
            val ny = y + posY

            try {
                result.add(grid[ny][nx])
            } catch (_: IndexOutOfBoundsException) {
                // noop
            }
        }

        return result
    }

    fun solvePart2(): Int {
        var rollSetup = InputUtils().readLines(4)

        var toRemove = 0

        var positions = getAccessibleRollPositions(rollSetup)

        while (positions.isNotEmpty()) {
            toRemove += positions.size
            rollSetup = removeRolls(rollSetup, positions)
            /*
            rollSetup.forEach { println(it) }
            println()
             */
            positions = getAccessibleRollPositions(rollSetup)
        }

        return toRemove
    }

    private fun removeRolls(
        rollSetup: List<String>,
        toRemove: ArrayList<Pair<Int, Int>>
    ): List<String> {
        val mutableRows = rollSetup.map { it.toCharArray() }

        for ((x, y) in toRemove) {
            if (y in mutableRows.indices && x in mutableRows[y].indices) {
                mutableRows[y][x] = '.'
            }
        }

        return mutableRows.map { String(it) }
    }

}

