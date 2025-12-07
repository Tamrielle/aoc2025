import utils.InputUtils

class Day7 {

    val input = InputUtils().readLines(7)

    fun solvePart1(): Int {
        val start = input.first().indexOfFirst { it == 'S' }
        var beamIndexes = arrayListOf(start)
        var solution = 0

        input.forEach { line ->
            // splitters that were hit by a beam
            val splitterIndices = line.withIndex()
                .filter { it.value == '^' && beamIndexes.contains(it.index) }
                .map { it.index }

            splitterIndices.forEach {
                val leftIndex = it - 1
                val rightIndex = it + 1
                if (!beamIndexes.contains(leftIndex))
                    beamIndexes.add(leftIndex)
                if (!beamIndexes.contains(rightIndex))
                    beamIndexes.add(rightIndex)

                // remove the splitter from the list
                beamIndexes.remove(it)
                solution += 1
            }

            beamIndexes = beamIndexes.sorted().toCollection(ArrayList())
        }

        return solution
    }

    fun solvePart2(): Int {
        return 0
    }

}



