package com.example.androidbaseuiunittest

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import java.time.LocalDate

class JUnitTest {
    init {
        println("new LifecycleTest")
    }

    @BeforeEach
    fun setUp() {
        println("setUp")
    }

    @DisplayName("HA HA")
    @Test
    fun a() {
        println("A")
    }

    @Test
    fun b() {
        println("B")
    }

    @After
    fun tearDown() {
        println("tearDown")
    }

}