package com.example.mss.chapter2

import com.example.mss.chapter2.service.AdvanceService
import com.example.mss.chapter2.service.ExampleService
import com.example.mss.chapter2.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class Chapter2Application {
	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='simple'}")
	fun exampleService() = ExampleService()

	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='advance'}")
	fun advanceService() = AdvanceService()
}

@Controller
class FirstController {
	@Autowired
	lateinit var service: IService

	@RequestMapping(value = "/user", method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello() = "hello world"

	@RequestMapping(value = "/users/{name}", method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello(@PathVariable name: String) = service.hello(name)

	@RequestMapping(value = "/message", method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun messageText() = service.messageText()

}

fun main(args: Array<String>) {
	runApplication<Chapter2Application>(*args)
}
