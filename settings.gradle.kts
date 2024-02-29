pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Purple"
include(":app")
include(":core:data")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:domain")
include(":core:analytics")
include(":feature:locations")
include(":build-logic")
