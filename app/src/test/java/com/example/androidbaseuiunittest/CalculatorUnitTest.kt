package com.example.androidbaseuiunittest

import com.example.androidbaseuiunittest.calculator.Calculator
import com.example.androidbaseuiunittest.calculator.CalculatorDto
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUnitTest {
    @Mock
    lateinit var calculatorMockData: CalculatorDto

    private val calculator = Calculator()

    @Before
    fun initMock() {
        calculatorMockData = mock(CalculatorDto::class.java)
    }

    @Test
    fun `정상적인 숫자가 들어갔을 때 올바르게 값을 리턴하는지에 대한 테스트`() {
        val expectedFirstNumber = 10
        val expectedSecondNumber = 5
        `when`(calculatorMockData.firstNumber).thenReturn(expectedFirstNumber)
        `when`(calculatorMockData.secondNumber).thenReturn(expectedSecondNumber)
        calculator.doCalculate(calculatorMockData)
        assertTrue(calculator.getAddResult() == (expectedFirstNumber + expectedSecondNumber))
        assertTrue(calculator.getSubtractResult() == (expectedFirstNumber - expectedSecondNumber))
        assertTrue(calculator.getMultiplyResult() == (expectedFirstNumber * expectedSecondNumber))
        assertTrue(calculator.getDivideResult() == (expectedFirstNumber.toFloat() / expectedSecondNumber.toFloat()))
    }

    @Test
    fun `숫자 둘중 하나가 빈칸이거나 널일때 예외처리가 잘 되는지에 대한 테스트`() {
        val expectedFirstNumber = 10
        val expectedSecondNumber = null
        `when`(calculatorMockData.firstNumber).thenReturn(expectedFirstNumber)
        `when`(calculatorMockData.secondNumber).thenReturn(expectedSecondNumber)
        assertNotNull(calculatorMockData.firstNumber)
        assertNull(calculatorMockData.secondNumber)
        try {
            `when`(calculator.doCalculate(calculatorMockData)).thenThrow()
        } catch (e: IllegalAccessException) {
            assertEquals(
                e.message,
                "Your number is Null. check first : $expectedFirstNumber, check second : $expectedSecondNumber"
            )
        }
    }

    @Test
    fun `숫자가 아닌 값이 들어올 경우 예외처리가 되는지에 대한 테스트`() {
        // 예외로 처리하였기에 Try-catch 를 이용하여 catch 에 내가 미리 입력한 Exception Message 가 잘 들어오는 지 확인
        try {
            // 숫자를 검증하는 코드가 생겼기에, 기대값이 달라짐
            val expectedFirstNumber = calculator.checkMyNumberIsNumber("11")
            val expectedSecondNumber = calculator.checkMyNumberIsNumber("Hello World")
            `when`(calculatorMockData.firstNumber).thenReturn(expectedFirstNumber)
            `when`(calculatorMockData.secondNumber).thenReturn(expectedSecondNumber)
            assertNotNull(calculatorMockData.firstNumber)
            assertNotNull(calculatorMockData.secondNumber)
            `when`(calculator.doCalculate(calculatorMockData))
        } catch (e: Exception) {
            // Exception message 와 에러 발생 시 나타나는 메시지가 일치하는지 어셜션 하라
            assertTrue(e.message!!.contains("is not Number"))
        }
    }
}