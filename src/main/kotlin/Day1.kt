class Day1 {

    fun readInput(): List<String> {
        val inputStream = this::class.java.classLoader.getResourceAsStream("day1.txt")
            ?: error("Resource not found!")

        return inputStream.bufferedReader().readLines()
    }

}