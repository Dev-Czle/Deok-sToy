package com.example.androidbaseuiunittest

import java.time.LocalDate

data class PayData(
    var billingDate: LocalDate? = null,
    var payAmount: Int? = null,
    var firstBillingDate : LocalDate? = null
) {


    companion object {

        fun builder() = Builder()

        class Builder {
            private val data = PayData()

            fun billingDate(billingDate: LocalDate): Builder {
                data.billingDate = billingDate
                return this
            }

            fun payAmount(payAmount: Int?): Builder {
                data.payAmount = payAmount
                return this
            }

            fun firstBillingDate(firstBillingDate : LocalDate) : Builder {
                data.firstBillingDate = firstBillingDate
                return this
            }

            fun build() = data
        }
    }
}
