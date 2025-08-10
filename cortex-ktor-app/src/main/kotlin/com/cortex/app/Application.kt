package com.cortex.app

<<<<<<< HEAD
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.CORS
=======
import io.ktor.server.application.*
>>>>>>> origin/main

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
<<<<<<< HEAD
    install(CORS) {
        allowHost("localhost:3000")
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.ContentType)
    }
=======
>>>>>>> origin/main
    configureHTTP()
    configureSerialization()
    configureRouting()
}
