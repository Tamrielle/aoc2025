import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class TestDay5 {
    private val day5 = Day5()

    @Test
    fun testSolvePart1() {
        val solution = day5.solvePart1()
        println(solution)
    }

    @Test
    fun testSolvePart2() {
        val solution = day5.solvePart2()
        println(solution)
    }

    @Test
    fun testRangeOverlap() {
        var range1 = 4L..10L
        var range2 = 5L..10L
        assertTrue(day5.rangeOverlapOrTouch(range1 ,range2))

        range1 = 4L..10L
        range2 = 11L..20L
        assertTrue(day5.rangeOverlapOrTouch(range1 ,range2))

        range1 = 4L..10L
        range2 = 4L..8L
        assertTrue(day5.rangeOverlapOrTouch(range1 ,range2))

        range1 = 12L..14L
        range2 = 4L..12L
        assertTrue(day5.rangeOverlapOrTouch(range1 ,range2))

        range1 = 12L..14L
        range2 = 4L..8L
        assertFalse(day5.rangeOverlapOrTouch(range1 ,range2))

        range1 = 4L..10L
        range2 = 12L..18L
        assertFalse(day5.rangeOverlapOrTouch(range1 ,range2))
    }



}
