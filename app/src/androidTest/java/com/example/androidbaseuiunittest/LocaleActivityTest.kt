package com.example.androidbaseuiunittest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LocaleActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(LocaleActivity::class.java)

    @Test
    fun noCountryExtra() {
        onView(withId(R.id.tvLocale)).check(matches(withText(R.string.no_country_extra)))
    }
    //https://codechacha.com/ko/android-test-espresso/
}