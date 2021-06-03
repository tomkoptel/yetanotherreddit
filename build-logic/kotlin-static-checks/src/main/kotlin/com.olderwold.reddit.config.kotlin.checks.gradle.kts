plugins {
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
}

ktlint {
    debug.set(false)
    version.set("0.40.0")
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    filter {
        exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
        include("**/kotlin/**")
    }
}

detekt {
    failFast = true
    parallel = true

    reports {
        html {
            enabled = true
            destination = file("build/reports/detekt.html")
        }
    }
}

val ktlint = tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask>()
val detekt = tasks.withType<io.gitlab.arturbosch.detekt.Detekt>() {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}
tasks.matching { it.name.contains("check") }
    .configureEach {
        this.dependsOn(ktlint)
        this.dependsOn(detekt)
    }

detekt.configureEach {
    exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
}
