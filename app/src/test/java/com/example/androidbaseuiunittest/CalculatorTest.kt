package com.example.androidbaseuiunittest

import com.example.androidbaseuiunittest.calculator.Calculator
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun plus() {
        val result: Int = Calculator.plus(1, 2)
        assertEquals(3, result)
        assertEquals(5, Calculator.plus(4, 1))
    }
}