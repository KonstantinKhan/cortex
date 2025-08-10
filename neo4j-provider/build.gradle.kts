plugins {
    kotlin("jvm") version "2.2.0"
}

group = "com.cortex"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.neo4j.driver)
    implementation(libs.kotlin.coroutines)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}