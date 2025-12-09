import utils.InputUtils

class Day8 {

    val input = InputUtils().readLines(8)

    fun solvePart1(): Int {
        val boxPositions = transformInput()
        val distances = calculateDistances(boxPositions)
        val sorted = distances.sortedBy { it.distance }.take(1000)

        val circuit: ArrayList<ArrayList<Position>> = arrayListOf()
        boxPositions.forEach {
            circuit.add(arrayListOf(it))
        }

        sorted.forEach { junction ->
            val firstIndex = circuit.indexOfFirst { it.contains(junction.pos1) }
            val secondIndex = circuit.indexOfFirst { it.contains(junction.pos2) }

            if (firstIndex == secondIndex) {
                return@forEach
            }

            circuit[firstIndex].addAll(circuit[secondIndex])
            circuit.removeAt(secondIndex)
        }

        val bestCircuits = circuit.sortedByDescending { it.size }.take(3)

        return bestCircuits.fold(1) { acc, circuit -> acc * circuit.size }
    }

    private fun transformInput(): ArrayList<Position> {
        val boxPositions = arrayListOf<Position>()
        input.forEach {
            val split = it.split(",")
            boxPositions.add(Position(split[0].toInt(), split[1].toInt(), split[2].toInt()))
        }
        return boxPositions
    }

    fun solvePart2(): Long {
        val boxPositions = transformInput()
        val distances = calculateDistances(boxPositions)
        val sorted = distances.sortedBy { it.distance }

        val circuit: ArrayList<ArrayList<Position>> = arrayListOf()
        boxPositions.forEach {
            circuit.add(arrayListOf(it))
        }

        var result = 0
        for (junction in sorted) {
            val firstIndex = circuit.indexOfFirst { it.contains(junction.pos1) }
            val secondIndex = circuit.indexOfFirst { it.contains(junction.pos2) }

            if (firstIndex == secondIndex) {
                continue
            }

            if (circuit.size == 2) {
                result = junction.pos1.x * junction.pos2.x
                break
            }

            circuit[firstIndex].addAll(circuit[secondIndex])
            circuit.removeAt(secondIndex)
        }

        return result.toLong()
    }

    private fun calculateDistances(boxPositions: List<Position>): ArrayList<Distance> {
        val distances = arrayListOf<Distance>()

        boxPositions.forEachIndexed { i, pos1 ->
            boxPositions.drop(i + 1).forEachIndexed { j, pos2 ->
                if (i == j) {
                    return@forEachIndexed
                }

                val distance = straightLineDistance(pos1, pos2)
                distances.add(Distance(pos1, pos2, distance))
            }
        }
        return distances
    }

    private fun straightLineDistance(
        pos1: Position,
        pos2: Position
    ): Int {
        val dx = (pos2.x - pos1.x).toDouble()
        val dy = (pos2.y - pos1.y).toDouble()
        val dz = (pos2.z - pos1.z).toDouble()
        return kotlin.math.sqrt(dx * dx + dy * dy + dz * dz).toInt()
    }

    data class Distance(var pos1: Position, var pos2: Position, var distance: Int)

    data class Position(var x: Int, var y: Int, var z: Int) {
        override fun toString(): String {
            return "($x,$y,$z)"
        }
    }

}