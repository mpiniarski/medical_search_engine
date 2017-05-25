package com.joma.studies

import com.google.inject.Guice
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    Guice.createInjector(AppModule())
    SpringApplication.run(App::class.java, *args)
}

