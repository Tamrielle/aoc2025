package utils

class InputUtils {

    fun readLines(forDay: Int): List<String> {
        val inputStream = this::class.java.classLoader.getResourceAsStream("day${forDay}.txt")
            ?: error("Resource not found!")
        return inputStream.bufferedReader().readLines()
    }

    fun readText(forDay: Int): String {
        val inputStream = this::class.java.classLoader.getResourceAsStream("day${forDay}.txt")
            ?: error("Resource not found!")
        return inputStream.bufferedReader().readText()
    }

}