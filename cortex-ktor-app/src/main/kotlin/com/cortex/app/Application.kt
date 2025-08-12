package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import kotlinx.serialization.json.Json

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(
    config: AppKtorConfig = AppKtorConfig(environment)
) {
    install(CORS) {
        allowHost("localhost:3000")
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowHeader(HttpHeaders.ContentType)
    }
    configureHTTP()
    configureSerialization()
    configureRouting(config)
}
