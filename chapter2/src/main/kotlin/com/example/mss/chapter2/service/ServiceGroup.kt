package com.example.mss.chapter2.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

interface IService {
    fun hello(name: String): String
    fun messageText(): String
}

class AdvanceService: IService {
    @Value(value = "\${service.message.text}")
    private lateinit var messageText: String
    private var count = 1

    override fun hello(name: String) = "$messageText $name (${count++})"
    override fun messageText() = messageText
}

// 서비스 어노테이션을 제거하면 명시적으로 Chapter2Application 에서 선언해야 한다.
//@Service
class ExampleService: IService {
    @Value(value = "\${service.message.text}")
    private lateinit var messageText: String

    @Value(value = "#{4+3}")
    private lateinit var result1: Number

//    @Value(value = "#{ \${one.value} div \${another.value} }")
//    private lateinit var result2: Comparable<Boolean>
//
//    @Value(value = "#{ \${one.value} eq \${another.value} }")
//    private lateinit var result3: Comparable<Boolean>
//
//    @Value(value = "#{ \${one.value} and \${another.value} }")
//    private lateinit var result4: Comparable<Boolean>
//
//    @Value(value = "#{ '\${some.value}' matches '[a-zA-Z\\s]+' }")
//    private lateinit var result6: Comparable<Boolean>


    override fun hello(name: String) = "$messageText $name used interface"
    override fun messageText() = messageText
}
