package com.example.androidbaseuiunittest.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.isDigitsOnly
import com.example.androidbaseuiunittest.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        val calculator = Calculator()

        // 버튼을 클릭했을 때
        doCalculate.setOnClickListener {
            // 첫번째 숫자가 비어있거나 Null 이면 null, 그게 아니라면 적혀있는 문자를 Int화 하여 초기화한다.
            val firstNumber = if (firstNumber.text.isNullOrEmpty()) {
                null
            } else {
                firstNumber.text.toString().toInt()
            }

            // 두번째 숫자가 비어있거나 Null 이면 null, 그게 아니라면 적혀있는 문자를 Int화 하여 초기화한다.
            val secondNumber = if (secondNumber.text.isNullOrEmpty()) {
                null
            } else {
                secondNumber.text.toString().toInt()
            }

            // 첫번째 숫자와 두번째 숫자를 이용하여 계산을 진행.
            calculator.doCalculate(CalculatorDto(firstNumber, secondNumber))

            // 각 연산에 따라 나온 결과값을 해당 텍스트에 입력해준다.
            addResultView.text = calculator.getAddResult().toString()
            subtractResultView.text = calculator.getSubtractResult().toString()
            divideResultView.text = calculator.getDivideResult().toString()
            multiplyResultView.text = calculator.getMultiplyResult().toString()
            errorResultView.setText(R.string.error_default)
        }
    }
}