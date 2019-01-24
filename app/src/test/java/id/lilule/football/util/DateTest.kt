package id.lilule.football.util

import id.lilule.football.util.Date.dateIsoToLong
import org.junit.Assert.assertEquals
import org.junit.Test

class DateTest {

    @Test
    fun posTestDateIsoToLong() {
        val isoDate = "2019-01-16"
        assertEquals("Wed, Jan 16, 2019", dateIsoToLong(isoDate))
    }

    @Test
    fun emptyTestDateIsoToLong() {
        val isoDate = ""
        assertEquals("", dateIsoToLong(isoDate))
    }

    @Test
    fun outTestDateIsoToLong() {
        val isoDate = "2019-99-99"
        assertEquals("", dateIsoToLong(isoDate))
    }

    @Test
    fun wrongTestDateIsoToLong() {
        val isoDate = "xxx"
        assertEquals("", dateIsoToLong(isoDate))
    }
}