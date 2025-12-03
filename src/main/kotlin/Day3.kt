import utils.InputUtils
import utils.removeLast

class Day3 {

    val input = InputUtils().readLines(3)

    fun solvePart1(): Long {
        var outputJoltage = 0L
        input.forEach { bank ->
            val largestJoltage = findMaxJoltage(bank, 2)
            outputJoltage += largestJoltage.toLong()
        }
        return outputJoltage
    }

    /*
    private fun findMaxJoltage(bank: String): String {
        val digits = bank.map { it.digitToInt() }.distinct().sortedDescending()
        val max = digits[0]
        val secondMax = digits[1]

        val maxIndex = bank.indexOf(max.toString())
        val secondIndex = bank.indexOf(secondMax.toString())

        val isLast = maxIndex == bank.length - 1
        if (isLast) {
            val subBank = bank.drop(secondIndex + 1)
            val maxSecond = subBank.max()

            return "$secondMax$maxSecond"
        }

        val subBank = bank.drop(maxIndex + 1)
        val maxSecond = subBank.max()

        return "$max$maxSecond"
    }
     */

    fun solvePart2(): Long {
        var outputJoltage = 0L
        input.forEach { bank ->
            val largestJoltage = findMaxJoltage(bank, 12)
            outputJoltage += largestJoltage.toLong()
        }
        return outputJoltage
    }


    fun findMaxJoltage(bank: String, numBatteries: Int): String {
        val numToRemove = bank.length - numBatteries
        var deletionsLeft = numToRemove
        val turnedOn = StringBuilder()

        for (battery in bank) {
            while (deletionsLeft > 0 && turnedOn.isNotEmpty() && turnedOn.last() < battery) {
                turnedOn.removeLast()
                deletionsLeft--
            }
            turnedOn.append(battery)
        }

        return turnedOn.substring(0, numBatteries)
    }


}

