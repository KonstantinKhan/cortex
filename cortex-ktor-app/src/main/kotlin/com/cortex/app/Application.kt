package com.cortex.app

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.CORS

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CORS) {
        allowHost("localhost:3000")
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.ContentType)
    }
    configureHTTP()
    configureSerialization()
    configureRouting()
}
