package com.quijotelui

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder

//Descomentar para generar archivo .war

@SpringBootApplication
class QuijoteLuiApplication /*: SpringBootServletInitializer()*/ {

//    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
//        return application.sources(QuijoteLuiApplication::class.java!!)
//    }

}

fun main(args: Array<String>) {
    SpringApplication.run(QuijoteLuiApplication::class.java, *args)
}
