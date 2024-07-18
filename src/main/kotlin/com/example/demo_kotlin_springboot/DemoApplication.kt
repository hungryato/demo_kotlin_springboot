package com.example.demo_kotlin_springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Declaring classes - class DemoApplication
 *
 * Right after package declaration and import statements you can see the first class declaration, class DemoApplication.
 * In Kotlin, if a class doesn't include any members (properties or functions), you can omit the class body ({}) for good.
 */

/**
 * @SpringBootApplication annotation
 *
 * @SpringBootApplication annotation is a convenience annotation in a Spring Boot application.
 * It enables Spring Boot's auto-configuration, component scan, and be able to define an extra configuration on their "application class".
 */
@SpringBootApplication
class DemoApplication

/**
 * Program entry point - main()
 *
 * The main() function is the entry point to the application.
 * It is declared as a top-level function outside the DemoApplication class. The main() function invokes the Spring's runApplication(*args) function to start the application with the Spring Framework.
 */

/**
 * Variable arguments - args: Array<String>
 *
 * If you check the declaration of the runApplication() function, you will see that the parameter of the function is marked with vararg modifier: vararg args: String.
 * This means that you can pass a variable number of String arguments to the function.
 */
fun main(args: Array<String>) {
	/**
	 * The spread operator - (*args)
	 *
	 * The args is a parameter to the main() function declared as an array of Strings.
	 * Since there is an array of strings, and you want to pass its content to the function, use the spread operator (prefix the array with a star sign *).
	 */
	runApplication<DemoApplication>(*args)
}