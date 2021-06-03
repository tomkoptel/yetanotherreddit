dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
    resolutionStrategy {
        eachPlugin {
            val id = requested.id.id
            if (id == "com.vanniktech.android.junit.jacoco") {
                useModule("com.vanniktech:gradle-android-junit-jacoco-plugin:0.17.0-SNAPSHOT")
            }
        }
    }
}

rootProject.name = "yet-another-reddit"

includeBuild("build-logic")
include(":app")
