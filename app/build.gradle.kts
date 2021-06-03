plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val composeVersion = "1.0.0-beta07"

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.olderwold.reddit"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        maybeCreate("release").apply {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    // region Dagger Hilt
    val hiltVersion: String by project

    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    // endregion

    // region Paging
    val pagingVersion = "3.0.0"

    implementation("androidx.paging:paging-runtime:$pagingVersion")
    testImplementation("androidx.paging:paging-common:$pagingVersion")
    implementation("androidx.paging:paging-compose:1.0.0-alpha09")
    // endregion

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-alpha08")

    // region Network
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val okhttpVersion = "4.4.1"
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    val moshiVersion = "1.12.0"
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    val okreplayVersion = "1.6.0"
    testImplementation("com.airbnb.okreplay:okreplay:$okreplayVersion")
    testImplementation("com.airbnb.okreplay:junit:$okreplayVersion")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.amshove.kluent:kluent:1.65")
    // endregion

    implementation("androidx.browser:browser:1.3.0")

    val mockkVersion = "1.9.3"
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.mockk:mockk-dsl:$mockkVersion")
}
