plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") {
        content {
            includeGroup("org.jetbrains.kotlinx")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.16.0")
}
