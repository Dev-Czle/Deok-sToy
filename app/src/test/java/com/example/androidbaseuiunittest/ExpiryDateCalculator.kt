package com.example.androidbaseuiunittest

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addedMonths = if (payData.payAmount == 100000) {
            12
        } else {
            payData.payAmount?.div(10000L) ?: 0
        }
        if (payData.firstBillingDate != null) {
            val candidateExp =
                payData.billingDate?.plusMonths(addedMonths) ?: LocalDate.of(1991, 3, 29)

            val dayOfFirstBilling = payData.firstBillingDate?.dayOfMonth ?: 0
            return if (dayOfFirstBilling != candidateExp.dayOfMonth) {
                val dayLenOfCandiMon = lastDayOfMonth(candidateExp)
                if (dayLenOfCandiMon < dayOfFirstBilling) {
                    candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth())
                } else {
                    candidateExp.withDayOfMonth(dayOfFirstBilling)
                }
            } else {
                payData.billingDate?.plusMonths(addedMonths)!!
            }
        }
        return payData.billingDate?.plusMonths(addedMonths)!!
    }

    private fun lastDayOfMonth(date: LocalDate): Int {
        return YearMonth.from(date).lengthOfMonth()
    }
}