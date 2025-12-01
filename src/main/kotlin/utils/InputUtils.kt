package utils

class InputUtils {

    fun readInput(forDay: Int): List<String> {
        val inputStream = this::class.java.classLoader.getResourceAsStream("day${forDay}.txt")
            ?: error("Resource not found!")
        return inputStream.bufferedReader().readLines()
    }

}