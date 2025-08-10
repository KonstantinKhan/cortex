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
    implementation(projects.cortextCommon)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}