plugins {
    kotlin("jvm") version "2.2.0"
}

group = "com.cortex"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.janusgraph.driver)
    implementation(libs.logback.classic)

    implementation(projects.cortexCommon)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}