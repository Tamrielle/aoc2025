import utils.InputUtils
import kotlin.math.abs

class Day9 {

    val input = InputUtils().readLines(9)

    fun solvePart1(): Long {
        val positions = transformInput()

        val listOfGridSizes = arrayListOf<Long>()

        for (i in positions.indices) {
            for (j in positions.indices) {
                if (i == j) continue

                val pos1 = positions[i]
                val pos2 = positions[j]

                val xDistance = abs(pos2.x - pos1.x) + 1
                val yDistance = abs(pos2.y - pos1.y) + 1

                val gridSize = xDistance.toLong() * yDistance.toLong()
                //println("$pos1 -> $pos2 = $gridSize")
                listOfGridSizes.add(gridSize)
            }
        }

        return listOfGridSizes.max()
    }

    private fun transformInput(): ArrayList<Position> =
        input.map { line ->
            val (x, y) = line.split(",").map(String::toInt)
            Position(x, y)
        }.toCollection(ArrayList())


    fun solvePart2(): Long {
        val coloredTiles = arrayListOf<Position>()

        val positions = transformInput()

        val maxX = positions.maxOf { it.x } + 2
        val maxY = positions.maxOf { it.y }
        val mutableGrid = MutableList(maxY + 1) {
            CharArray(maxX + 1) { '.' }
        }

        for (i in positions.indices) {
            val pos1 = positions[i]
            mutableGrid[pos1.y][pos1.x] = '#'

            for (j in i + 1 until positions.size) {
                val pos2 = positions[j]
                mutableGrid[pos2.y][pos2.x] = '#'

                // horizontal line
                if (pos1.y == pos2.y) {
                    val range = if (pos1.x < pos2.x) pos1.x..pos2.x else pos2.x..pos1.x
                    for (x in range) {
                        if (mutableGrid[pos1.y][x] != '#') {
                            mutableGrid[pos1.y][x] = 'X'
                        }
                    }
                }

                // vertical line
                if (pos1.x == pos2.x) {
                    val range = if (pos1.y < pos2.y) pos1.y..pos2.y else pos2.y..pos1.y
                    for (y in range) {
                        if (mutableGrid[y][pos1.x] != '#') {
                            mutableGrid[y][pos1.x] = 'X'
                        }
                    }
                }

                // inside
            }
        }

        fillBetweenBounds(mutableGrid)

        mutableGrid.forEach { println(it.joinToString("")) }



        return 0
    }

    fun fillBetweenBounds(grid: MutableList<CharArray>) {
        for (y in grid.indices) {
            val row = grid[y]
            var lastSolid = -1

            for (x in row.indices) {
                if (row[x] != '.') {
                    if (lastSolid != -1 && x > lastSolid + 1) {
                        for (fillX in lastSolid + 1 until x) {
                            row[fillX] = 'X'
                        }
                    }
                    lastSolid = x
                }
            }
        }
    }

    data class Position(var x: Int, var y: Int) {
        override fun toString(): String {
            return "($x,$y)"
        }
    }
}
