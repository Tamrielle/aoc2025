import utils.InputUtils

class Day2 {

    val input = InputUtils().readText(2)
    val ranges: ArrayList<IdRange> = convertToRange(input)

    var invalidValue = 0L

    fun solvePart1(): Long {
        ranges.forEach {
            for (id in it.min..it.max) {
                val isIdValid = checkId(id)
                if (!isIdValid) {
                    invalidValue += id
                }
            }
        }

        return invalidValue
    }

    private fun checkId(id: Long): Boolean {
        val converted = id.toString()
        if (converted.length % 2 != 0) {
            return true
        }

        val mid = converted.length / 2
        val first = converted.take(mid)
        val second = converted.drop(mid)

        return first != second
    }

    fun solvePart2(): Long {
        ranges.forEach {
            for (id in it.min..it.max) {
                val isIdValid = doubleCheckId(id)
                if (!isIdValid) {
                    invalidValue += id
                }
            }
        }

        return invalidValue
    }

    private fun doubleCheckId(id: Long): Boolean {
        val converted = id.toString()

        val divisors = divisors(converted.length)
        divisors.removeFirst()
        divisors.forEach { divisor ->
            val split = splitByDivisor(converted, divisor)
            val allSame = split.distinct().size == 1
            if (allSame) {
                return false
            }
        }

        return true
    }

    fun splitByDivisor(number: String, divisor: Int): List<String> {
        val length = number.length

        if (length % divisor != 0) return emptyList()

        val chunkSize = length / divisor
        return number.chunked(chunkSize)
    }

    fun divisors(n: Int): ArrayList<Int> {
        val list = arrayListOf<Int>()
        for (number in 1..n) {
            if (n % number == 0) list.add(number)
        }
        return list
    }

    private fun convertToRange(input: String): ArrayList<IdRange> {
        val idRanges = input.split(",")

        val ranges: ArrayList<IdRange> = arrayListOf()
        idRanges.forEach {
            val rangeString = it.split("-")
            val min = rangeString[0].trim().toLong()
            val max = rangeString[1].trim().toLong()

            ranges.add(IdRange(min, max))
        }

        return ranges
    }

}

data class IdRange(val min: Long, val max: Long)
