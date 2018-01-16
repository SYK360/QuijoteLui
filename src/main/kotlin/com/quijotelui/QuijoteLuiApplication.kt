/*
Modo developer
 */
package com.quijotelui

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class QuijoteLuiApplication

fun main(args: Array<String>) {
    SpringApplication.run(QuijoteLuiApplication::class.java, *args)
}


/*
Modo .war
 */
//package com.quijotelui
//
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.web.support.SpringBootServletInitializer
//import org.springframework.boot.builder.SpringApplicationBuilder
//
//
//@SpringBootApplication
//class QuijoteLuiApplication : SpringBootServletInitializer() {
//
//    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
//        return application.sources(QuijoteLuiApplication::class.java!!)
//    }
//
//}
//
//fun main(args: Array<String>) {
//    SpringApplication.run(QuijoteLuiApplication::class.java, *args)
//}

/*
Modo developer
 */
//package com.quijotelui
//
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.SpringBootApplication
//
//
//@SpringBootApplication
//class QuijoteLuiApplication
//
//fun main(args: Array<String>) {
//    SpringApplication.run(QuijoteLuiApplication::class.java, *args)
//}
