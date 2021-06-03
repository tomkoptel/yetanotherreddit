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
    id("com.vanniktech.android.junit.jacoco")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

the<com.vanniktech.android.junit.jacoco.JunitJacocoExtension>().run {
    val defaultExcludes = this.excludes
    jacocoVersion = "0.8.7"
    excludes = listOf(
        defaultExcludes,
        listOf(
            "**/*Binding*.*",
            "**/Hilt_*.*",
            "**/*ModuleDeps*.*",
            "**/*Binder*.*",
            "**/*Realm*.*",
            "**/*_AssistedFactory*.*",
            "jdk.internal.*",
        )
    ).flatten()
    xml.isEnabled = true
    html.isEnabled = true
    csv.isEnabled = false
}
