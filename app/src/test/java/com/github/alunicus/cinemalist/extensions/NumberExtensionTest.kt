package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.data.Duration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NumberExtensionTest {
    @Nested
    inner class DurationTests {
        @Test
        fun `should return correct hours`() {
            val duration = 131.toDuration()

            assertThat(duration).isInstanceOf(Duration::class.java)
            assertThat(duration.hours).isEqualTo(2)
        }

        @Test
        fun `should return correct minutes`() {
            val duration = 131.toDuration()

            assertThat(duration).isInstanceOf(Duration::class.java)
            assertThat(duration.minutes).isEqualTo(11)
        }
    }
}