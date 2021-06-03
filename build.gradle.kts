buildscript {
    repositories {
        google()
        mavenCentral()
    }

    val hiltVersion: String by project
    val kotlinVersion: String by project
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

plugins {
    id("com.olderwold.reddit.config.kotlin.checks")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
