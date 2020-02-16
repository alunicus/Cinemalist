package com.github.alunicus.cinemalist.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class DateExtensionTest {
    @Test
    fun `should return year as Int from the Date`() {
        val date = Date(1580666402000) // 2020-02-02:20:00:02

        assertThat(date.asYear()).isEqualTo(2020)
    }
}