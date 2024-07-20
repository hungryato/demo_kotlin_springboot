package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import org.springframework.jdbc.core.query

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
	 * Retrieving a value from the context path
	 *
	 * The message id is retrieved from the context path by the Spring Framework as you annotated the new function by @GetMapping("/{id}").
	 * By annotating the function argument with @PathVariable, you tell the framework to use the retrieved value as a function argument.
	 * The new function makes a call to MessageService to retrieve the individual message by its id.
	 */
	@GetMapping("/{id}")
	fun index(@PathVariable id: String): List<Message> = service.findMessageById(id)

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

	/**
	 * vararg argument position in the parameter list
	 *
	 * The query() function takes three arguments:
	 * - SQL query string that requires a parameter to run
	 * - `id`, which is a parameter of type String
	 * - RowMapper instance is implemented by a lambda expression
	 * The second parameter for the query() function is declared as a variable argument (vararg).
	 * In Kotlin, the position of the variable arguments parameter is not required to be the last in the parameters list.
	 */
	fun findMessageById(id: String): List<Message> = db.query("select * from messages where id = ?", id) { response, _ ->
		Message(response.getString("id"), response.getString("text"))
	}

	fun save(message: Message) {
		val id = message.id ?: UUID.randomUUID().toString()
		db.update(
			"insert into messages values (?, ?)",
			id, message.text
		)
	}
}