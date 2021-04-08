package com.example.androidbaseuiunittest

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleMockito {

    //Mock 객체 생성
    @Mock
    private lateinit var user1: User // 어노테이션을 이용하는 방법
    private val user2 = mock(User::class.java) // mock() 메서드를 이용하는 방법

    // Stubbing 및 println 을 이용한 체킹
    @Test
    fun stubbing() {
        Mockito.`when`(user1.id).thenReturn(0) // user1.id 를 호출하면 0 을 리턴하라
        Mockito.`when`(user1.nickname).thenReturn("Kim")
        // user1.nickname 를 호출하면 Kim 을 리턴하라

        Mockito.`when`(user2.id).thenReturn(1) // user2.id 를 호출하면 1 을 리턴하라
        Mockito.`when`(user2.nickname).thenReturn("Lee")
        // user2.nickname 를 호출하면 Lee 을 리턴하라

        println(user1.id) // 0 출력
        println(user1.nickname) // Kim 출력

        println(user2.id) // 1 출력
        println(user2.nickname) // Lee 출력

    }

    @Test
    fun doThrow() {
        // doThrow 설정, id 를 28로 호출 시 NullPointerException 예외 발생
        doThrow(NullPointerException::class.java)
            .`when`(user1) // 협력 객체 설정
            .id = ArgumentMatchers.eq(28L) // eq 는 정확한 값을 의미함.
        user1.id = 30 // 성공
        user1.id = 29 // 성공
        user1.id = 28 // 예외 발생
    }

    @Test
    fun verify() {
        val user = mock(User::class.java)

        user.id = 1

        user.nickname = "Kim"
        user.nickname = "Lee"

        verify(user, times(1)).id = any(Long::class.java)
        verify(user, times(2)).nickname = any(String::class.java)

        verify(user, never()).nickname
        verify(user, never()).nickname = "Kim"

        verify(user, atLeastOnce()).id = any(Long::class.java)
        verify(user, atLeast(2)).id = any(Long::class.java)
        verify(user, atMost(2)).id = any(Long::class.java)

        verify(user, timeout(200)).id = any(Long::class.java)
        verify(user, timeout(200).atLeastOnce()).id = any(Long::class.java)
    }
}

data class User(var id: Long? = null, var nickname: String? = null)