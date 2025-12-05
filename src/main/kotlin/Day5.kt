import utils.InputUtils

class Day5 {

    val input = InputUtils().readLines(5)

    fun solvePart1(): Int {
        val ingredientIds = splitIngredientIds()

        val freshIdsSorted = ingredientIds.first
        val idsToCheck = ingredientIds.second

        var freshIds = 0
        idsToCheck.forEach { id ->
            val isIdFresh = checkIdForFreshness(freshIdsSorted, id.toLong())
            if (isIdFresh) {
                println("Ingredient with id: $id is fresh!")
                freshIds++
            }
        }

        return freshIds
    }

    private fun checkIdForFreshness(freshIds: List<LongRange>, ingredientId: Long): Boolean {
        return freshIds.any { ingredientId in it }
    }

    private fun splitIngredientIds(): Pair<List<LongRange>, List<String>> {
        val splitIndex = input.indexOf("")
        val (first, second) =
            if (splitIndex != -1) {
                input.take(splitIndex) to input.drop(splitIndex + 1)
            } else {
                input to emptyList()
            }

        val idRanges = arrayListOf<LongRange>()
        first.forEach {
            val (start, end) = it.split("-").map { it.toLong() }.toList()
            idRanges.add(start..end)
        }
        val sortedIds = idRanges.sortedBy { inner ->
            inner.first()
        }

        return Pair(sortedIds, second)
    }


    fun solvePart2(): Long {
        val ingredientIds = splitIngredientIds()

        val freshIdsSorted = ingredientIds.first
        val merged = freshIdsSorted.mergeRanges()

        var numFreshIds = 0L
        merged.forEach { freshIdRange ->
            numFreshIds += freshIdRange.last - freshIdRange.first + 1
        }

        return numFreshIds
    }

    fun List<LongRange>.mergeRanges(): List<LongRange> {
        val mergedList = mutableListOf<LongRange>()

        for (range in this) {
            val overlap = checkOverlap(mergedList, range)
            if (overlap != null) {
                // println("$range overlaps $overlap")
                val index = mergedList.indexOf(overlap)
                val merged = mergeRanges(overlap, range)
                mergedList[index] = merged
            } else {
                // println("$range does not overlap")
                mergedList.add(range)
            }
        }

        return mergedList
    }

    fun mergeRanges(a: LongRange, b: LongRange): LongRange {
        val start = minOf(a.first, b.first)
        val end = maxOf(a.last, b.last)
        return start..end
    }

    private fun checkOverlap(
        merged: MutableList<LongRange>,
        range: LongRange
    ): LongRange? {
        merged.forEach {
            if (rangeOverlapOrTouch(it, range)) {
                return it
            }
        }
        return null
    }

    fun rangeOverlapOrTouch(first: LongRange, second: LongRange): Boolean {
        return first.first <= second.last + 1 && second.first <= first.last + 1
    }

}


