package com.example.androidbaseuiunittest

import com.example.androidbaseuiunittest.tddtest.PasswordStrength
import com.example.androidbaseuiunittest.tddtest.PasswordStrengthMeter
import com.ibm.icu.impl.ICUCache.WEAK
import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordStrengthMeterTest {
    private val meter = PasswordStrengthMeter()

    private fun assertStrength(password: String?, expStr: PasswordStrength) {
        val result = meter.meter(password)
        assertEquals(expStr, result)
    }

    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG)
        assertStrength("abc1!Add", PasswordStrength.STRONG)
    }

    @Test
    fun meetOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL)
        assertStrength("Ab12!c", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL)
    }

    @Test
    fun nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID)
    }

    @Test
    fun meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdeefhi", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyNumberCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK)
    }

    @Test
    fun meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK)
    }
}