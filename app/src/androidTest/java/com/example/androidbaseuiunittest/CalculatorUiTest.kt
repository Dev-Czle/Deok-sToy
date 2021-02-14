package com.example.androidbaseuiunittest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidbaseuiunittest.calculator.CalculatorActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorUiTest {

    // Rule, 단일 액티비티에 접근하기 위한 어노테이션,
    // 해당 액티비티의 리소스를 가져오기 위해서는 Rule 어노테이션은 필수이다.
    @get:Rule
    var activityRule = ActivityScenarioRule(CalculatorActivity::class.java)

    // 해당 액티비티에 존재하는 리소스 아이디, 해당 테스트는 단일 액티비티를 이용하였기에 이곳에서 선언하지만 Intent 를 이용할 경우
    // Intent 이후 액티비티의 리소스 아이디들을 선언 시 NPE 가 뜰 수 있다.

    private val firstNumberViewId = R.id.firstNumber
    private val secondNumberViewId = R.id.secondNumber
    private val calculatorLaunchViewId = R.id.doCalculate
    private val addResultViewId = R.id.addResultView
    private val subtractResultViewId = R.id.subtractResultView
    private val multiplyResultViewId = R.id.multiplyResultView
    private val divideResultViewId = R.id.divideResultView
    private val errorResultViewId = R.id.errorResultView

    @Test
    fun ifViewHasCorrectNumbers() {
        // target activity 의 context 를 가져오는것
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // 첫번째 입력될 숫자
        val firstNumber = 10.toString()
        // 두번째 입력될 숫자
        val secondNumber = 5.toString()
        // 연산 후 기대값
        val expectedAddResult = 15.toString()
        val expectedSubtract = 5.toString()
        val expectedMultiplyResult = 50.toString()
        val expectedDivideResult = 2.toFloat().toString()
        // 문제점 view 의 기본 메시지
        val defaultErrorMessage = context.getString(R.string.error_default)

        // 숫자 입력
        onView(withId(firstNumberViewId)).perform(typeText(firstNumber)).check(matches(withText(firstNumber)))
        onView(withId(secondNumberViewId)).perform(typeText(secondNumber)).check(matches(withText(secondNumber)))
        // 버튼 클릭
        onView(withId(calculatorLaunchViewId)).perform(click())
        // 버튼 클릭 후 결과확인
        onView(withId(addResultViewId)).check(matches(withText(expectedAddResult)))
        onView(withId(subtractResultViewId)).check(matches(withText(expectedSubtract)))
        onView(withId(multiplyResultViewId)).check(matches(withText(expectedMultiplyResult)))
        onView(withId(divideResultViewId)).check(matches(withText(expectedDivideResult)))
        onView(withId(errorResultViewId)).check(matches(withText(defaultErrorMessage)))
    }

    @Test
    fun ifViewHasNull() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val firstNumber = 10.toString()
        val secondNumber = null
        val defaultAddMessage = context.getString(R.string.add_default)
        val defaultSubtractMessage = context.getString(R.string.subtract_default)
        val defaultMultiplyMessage = context.getString(R.string.multiply_default)
        val defaultDivideMessage = context.getString(R.string.divide_default)
        val errorMessage =
            "Your number is Null. check first : $firstNumber, check second : $secondNumber"

        onView(withId(firstNumberViewId)).perform(typeText(firstNumber))
            .check(matches(withText(firstNumber)))

        onView(withId(calculatorLaunchViewId)).perform(click())

        onView(withId(addResultViewId)).check(matches(withText(defaultAddMessage)))
        onView(withId(subtractResultViewId)).check(matches(withText(defaultSubtractMessage)))
        onView(withId(multiplyResultViewId)).check(matches(withText(defaultMultiplyMessage)))
        onView(withId(divideResultViewId)).check(matches(withText(defaultDivideMessage)))

        onView(withId(errorResultViewId)).check(matches(withText(errorMessage)))
    }

    @Test
    fun ifViewHasNotNumber() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val firstNumber = 10.toString()
        val secondNumber = "Hello World!!"
        val defaultAddMessage = context.getString(R.string.add_default)
        val defaultSubtractMessage = context.getString(R.string.subtract_default)
        val defaultMultiplyMessage = context.getString(R.string.multiply_default)
        val defaultDivideMessage = context.getString(R.string.divide_default)
        val errorMessage = "숫자만 입력해주세요."

        onView(withId(firstNumberViewId)).perform(typeText(firstNumber)).check(matches(withText(firstNumber)))
        onView(withId(secondNumberViewId)).perform(typeText(secondNumber)).check(matches(withText(secondNumber)))

        onView(withId(calculatorLaunchViewId)).perform(click())

        onView(withId(addResultViewId)).check(matches(withText(defaultAddMessage)))
        onView(withId(subtractResultViewId)).check(matches(withText(defaultSubtractMessage)))
        onView(withId(multiplyResultViewId)).check(matches(withText(defaultMultiplyMessage)))
        onView(withId(divideResultViewId)).check(matches(withText(defaultDivideMessage)))

        onView(withId(errorResultViewId)).check(matches(withText(errorMessage)))
    }
}