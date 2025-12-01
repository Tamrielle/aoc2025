import kotlin.test.Test
import kotlin.test.assertTrue

class TestDay1 {
    private val day1 = Day1()

    @Test
    fun readInput_shouldReadResourceFile() {
        val lines = day1.readInput()

        println(lines)
        assertTrue(lines.isNotEmpty(), "File should not be empty")

    }


}
