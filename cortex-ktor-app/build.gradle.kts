plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.cors)
    implementation(libs.logback.classic)
    implementation(libs.neo4j.driver)

    implementation(projects.cortexCommon)
    implementation(projects.cortexTaskServiceModule)
    implementation(projects.cortexTaskRepository)
    implementation(projects.cortexMapping)
    implementation(projects.cortexTransport)

    implementation(libs.logback.classic)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
