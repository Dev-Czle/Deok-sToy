package com.example.androidbaseuiunittest

import android.os.Build
import android.widget.Button
import android.widget.TextView
import com.example.androidbaseuiunittest.calculator.CalculatorActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RobolectricTest {

    @Test
    fun test() {
        val activity: CalculatorActivity = Robolectric.setupActivity(CalculatorActivity::class.java)
        val button = activity.findViewById<Button>(R.id.doCalculate)
        val addTextView = activity.findViewById<TextView>(R.id.addResultView)

    }
}