rootProject.name = "cortex"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("cortex-ktor-app")
include("neo4j-provider")
include("cortex-task-repository")

include("cortex-ktor-app")
include("neo4j-provider")

include("cortext-common")