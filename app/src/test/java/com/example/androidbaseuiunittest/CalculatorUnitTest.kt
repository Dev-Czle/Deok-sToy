package com.example.androidbaseuiunittest

import android.app.Application
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
    // CalculatorDto 에 대한 Mock 변수를 선언해줍니다.
    @Mock
    lateinit var calculatorMockData: CalculatorDto

    // 실제 로직을 담당할 클래스를 생성자로 초기화 해줍니다.
    private val calculator = Calculator()

    // @Before 어노테이션을 이용하여 해당 테스트 시작 전에 calculatorMockData 를 초기화해주는 작업
    @Before
    fun initMock() {
        calculatorMockData = mock(CalculatorDto::class.java)
    }

    @Test
    fun `정상적인 숫자가 들어갔을 때 올바르게 값을 리턴하는지에 대한 테스트`() {
        // 기대값 1
        val expectedFirstNumber = 10
        // 기대값 2
        val expectedSecondNumber = 5
        // 각 결과값에 대한 기대값
        val expectedAdded = 15
        val expectedSubtract = 5
        val expectedMultiply = 50
        val expectedDivide = 2.0f
        // calculatorMockData.firstNumber 를 호출할때, expectedFirstNumber 로 선언한 10을 리턴하라
        `when`(calculatorMockData.firstNumber).thenReturn(expectedFirstNumber)
        // calculatorMockData.secondNumber 를 호출할때, expectedSecondNumber 로 선언한 5을 리턴하라
        `when`(calculatorMockData.secondNumber).thenReturn(expectedSecondNumber)
        // 계산함수를 실행
        calculator.doCalculate(calculatorMockData)
        // 각 계산된 값과 기대값이 일치하는지 확인
        assertTrue(calculator.getAddResult() == expectedAdded)
        assertTrue(calculator.getSubtractResult() == expectedSubtract)
        assertTrue(calculator.getMultiplyResult() == expectedMultiply)
        assertTrue(calculator.getDivideResult() == expectedDivide)
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
            `when`(calculator.doCalculate(calculatorMockData))
        } catch (e: IllegalAccessException) {
            println(e.message)
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