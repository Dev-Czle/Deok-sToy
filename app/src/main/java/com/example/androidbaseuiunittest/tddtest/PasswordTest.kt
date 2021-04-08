package com.example.androidbaseuiunittest.tddtest

enum class PasswordStrength {
    STRONG,
    NORMAL,
    INVALID,
    WEAK
}

class PasswordStrengthMeter {
    fun meter(s: String?): PasswordStrength? {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID
        }
        getMetCriteriaCounts(s).apply {
            if (this <= 1) {
                return PasswordStrength.WEAK
            } else if (this == 2) {
                return PasswordStrength.NORMAL
            }
        }

        return PasswordStrength.STRONG
    }

    private fun getMetCriteriaCounts(s: String): Int {
        var metCounts = 0
        if (s.length >= 8) metCounts++
        if (meetContainingNumberCriteria(s)) metCounts++
        if (meetContainingUppercaseCriteria(s)) metCounts++
        return metCounts
    }

    private fun meetContainingNumberCriteria(s: String?): Boolean {
        if (s != null) {
            for (ch in s) {
                if (ch in '0'..'9') {
                    return true
                }
            }
        }
        return false
    }

    private fun meetContainingUppercaseCriteria(s: String): Boolean {
        for (ch in s) {
            if (ch.isUpperCase()) {
                return true
            }
        }
        return false
    }
}