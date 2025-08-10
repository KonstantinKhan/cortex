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
    implementation(libs.kotlin.serialization)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}