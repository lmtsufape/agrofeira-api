package br.com.ecofeira

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EcofeiraApplication

fun main(args: Array<String>) {
	runApplication<EcofeiraApplication>(*args)
}
