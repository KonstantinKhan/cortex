rootProject.name = "cortex"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("cortex-ktor-app")
include("neo4j-provider")