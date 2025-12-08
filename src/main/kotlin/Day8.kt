import utils.InputUtils

class Day8 {

    val input = InputUtils().readLines(8)

    fun solvePart1(): Int {
        val boxPositions = arrayListOf<Position>()
        input.forEach {
            val split = it.split(",")
            boxPositions.add(Position(split[0].toInt(), split[1].toInt(), split[2].toInt()))
        }

        val circuits = arrayListOf<Circuit>()
        var connections: List<List<Connection>> = arrayListOf()

        while (boxPositions.size > 0) {
            val distances = arrayListOf<Distance>()

            boxPositions.forEachIndexed { i, pos1 ->
                boxPositions.forEachIndexed { j, pos2 ->
                    if (i == j) {
                        return@forEachIndexed
                    }

                    if (alreadyConnected(connections, pos1, pos2)) {
                        return@forEachIndexed
                    }

                    val distance = straightLineDistance(pos1, pos2)
                    distances.add(Distance(pos1, pos2, distance))
                }
            }

            val minDistance = distances.minByOrNull { it.distance }
            minDistance?.let {

                if (bothAreInConnection(connections, minDistance)) {
                    println("Finishing circuit with ${connections.size} connections.")
                    circuits.add(Circuit(connections))

                    connections.forEach {
                        boxPositions.remove(it.pos1)
                        boxPositions.remove(it.pos2)
                    }
                    connections = arrayListOf()
                } else {
                    val sorted = arrayListOf( it.pos1, it.pos2).sortedBy { it.x }

                    connections.add(Connection( sorted.first(),  sorted.last()))
                    println("connections: $connections")
                }
            }
        }

        println(circuits)

        return 0
    }

    private fun bothAreInConnection(connections: List<List<Connection>>, minDistance: Distance): Boolean {
        connections.forEach { connection ->
            if ( connection.find { it.pos1 == minDistance.pos1 || it.pos2 == minDistance.pos1 } != null &&
                connection.find { it.pos1 == minDistance.pos2 || it.pos2 == minDistance.pos2 } != null) {
                return true
            }
      }


        return false
    }

    private fun alreadyConnected(connections: List<List<Connection>>, pos1: Position, pos2: Position): Boolean {
        connections.forEach { connection ->
            if( connection.find { it.pos1 == pos1 && it.pos2 == pos2 } != null) {
                return true
            }
        }

        return false
    }

    fun solvePart2(): Long {
        return 0L
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

}

data class Circuit(val connections: ArrayList<Connection>)

data class Connection(var pos1: Position, var pos2: Position)

data class Distance(var pos1: Position, var pos2: Position, var distance: Int) {}

data class Position(var x: Int, var y: Int, var z: Int) {
    override fun toString(): String {
        return "($x,$y,$z)"
    }
}

