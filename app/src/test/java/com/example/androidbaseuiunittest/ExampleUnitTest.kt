package com.example.androidbaseuiunittest

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.androidbaseuiunittest.calculator.CalculatorActivity
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    @Test
    fun test(){
        val activityRule = ActivityScenarioRule(CalculatorActivity::class.java)
        activityRule.scenario.onActivity {
            
        }
    }
}