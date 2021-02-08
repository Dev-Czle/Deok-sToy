package com.example.androidbaseuiunittest.calculator

class Calculator {
    private lateinit var myNumbers: CalculatorDto // Dto 클래스, Java 의 Getter/Setter 의 역할을 해준다
    private var addResult: Int? = null // 더하기 결과값
    private var subtractResult: Int? = null // 빼기 결과값
    private var divideResult: Float? = null // 나누기 결과값
    private var multiplyResult: Int? = null // 곱하기 결과값

    // 계산을 실행하는 함수.
    fun doCalculate(myNumbers: CalculatorDto) {
        val isMyNumberIsNotNull = checkMyNumberIsNotNull(myNumbers)
        if (isMyNumberIsNotNull) {
            this.myNumbers = myNumbers
            addResult = add()
            subtractResult = subtract()
            multiplyResult = multiply()
            divideResult = divide()
        } else {
            throw IllegalAccessException(
                "Your number is Null. check first : " +
                        "${myNumbers.firstNumber}, check second : ${myNumbers.secondNumber}"
            )
        }
    }
    // 더하기 연산 함수
    private fun add(): Int {
        return myNumbers.firstNumber!! + myNumbers.secondNumber!!
    }
    // 빼기 연산 함수
    private fun subtract(): Int {
        return myNumbers.firstNumber!! - myNumbers.secondNumber!!
    }
    // 곱하기 연산 함수
    private fun multiply(): Int {
        return myNumbers.firstNumber!! * myNumbers.secondNumber!!
    }
    // 나누기 연산 함수
    private fun divide(): Float {
        return myNumbers.firstNumber!!.toFloat() / myNumbers.secondNumber!!.toFloat()
    }
    // 각각의 연산에 대한 결과값
    fun getAddResult() = addResult
    fun getSubtractResult() = subtractResult
    fun getMultiplyResult() = multiplyResult
    fun getDivideResult() = divideResult

    // 연산을 하기 위한 숫자들이 null 인지 체크하는 함수
    private fun checkMyNumberIsNotNull(myNumbers: CalculatorDto): Boolean {
        if (myNumbers.firstNumber == null || myNumbers.secondNumber == null) {
            return false
        }
        return true
    }
}

data class CalculatorDto(
    val firstNumber: Int? = null,
    val secondNumber: Int? = null
)