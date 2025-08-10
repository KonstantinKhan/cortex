plugins {
    kotlin("jvm") version "2.2.0"
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.cortex"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.neo4j.driver)
    implementation(libs.kotlin.coroutines)
    implementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}