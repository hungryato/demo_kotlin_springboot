package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@RestController
class MessageController(val service: MessageService) {
	@GetMapping("/")
	fun index(): List<Message> = service.findMessages()

	/**
	 * @PostMapping annotation
	 *
	 * The method responsible for handling HTTP POST requests needs to be annotated with @PostMapping annotation.
	 * To be able to convert the JSON sent as HTTP Body content into an object, you need to use the @RequestBody annotation for the method argument.
	 * Thanks to having Jackson library in the classpath of the application, the conversion happens automatically.
	 */
	@PostMapping("/")
	fun post(@RequestBody message: Message) {
		service.save(message)
	}
}

data class Message(val id: String?, val text: String)

@Service
class MessageService(val db: JdbcTemplate) {
	fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
		Message(response.getString("id"), response.getString("text"))
	}

	fun save(message: Message) {
		db.update(
			"insert into messages values (?, ?)",
			message.id, message.text
		)
	}
}