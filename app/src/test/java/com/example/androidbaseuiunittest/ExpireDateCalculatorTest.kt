package com.example.androidbaseuiunittest

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class ExpireDateCalculatorTest {

    @Test
    fun `만원 납부하면 한달 뒤가 만료일이 됨()`() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 31))
                .payAmount(10000)
                .build(),
            LocalDate.of(
                2019, 4, 30
            )
        )

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 5))
                .payAmount(10000)
                .build(),
            LocalDate.of(
                2019, 6, 5
            )
        )
    }

    @Test
    fun `납부일과 한달 뒤 일자가 같지 않음`() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(10000)
                .build(),
            LocalDate.of(
                2019, 2, 28
            )
        )
    }

    @Test
    fun `첫 납부일과 만료일 일자가 다를 때 만원 납부`() {
        val payData = PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 31))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10000)
            .build()

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31))

        val payData2 = PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 30))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10000)
            .build()

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30))

        val payData3 = PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 5, 31))
            .billingDate(LocalDate.of(2019, 6, 30))
            .payAmount(10000)
            .build()

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31))
    }

    @Test
    fun `이만원 이상 납부하면 비례해서 만료일 계산`() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(20000)
                .build(),
            LocalDate.of(2019, 5, 1)
        )

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(30000)
                .build(),
            LocalDate.of(2019, 6, 1)
        )
    }

    @Test
    fun `첫 납부일과 만료일 일자가 다를때 이만원 이상 납부`() {
        assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20000)
                .build(),
            LocalDate.of(2019, 4, 30)
        )
    }

    @Test
    fun `십만원을 납부하면 1년 제공`() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 28))
                .payAmount(100000)
                .build(),
            LocalDate.of(2020, 1, 28)
        )
    }

    private fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        ExpiryDateCalculator().apply {
            assertEquals(expectedExpiryDate, calculateExpiryDate(payData))
        }
    }
}