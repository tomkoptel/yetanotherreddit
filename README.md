# Reddit Client Demo App

![Android CI with Gradle](https://github.com/tomkoptel/yetanotherreddit/workflows/Android%20CI%20with%20Gradle/badge.svg)

## Get the App
You can find the latest version inside [Actions Tab](https://github.com/tomkoptel/sliide-task/actions).

# App
![App](https://github.com/tomkoptel/yetanotherreddit/blob/develop/app.gif?raw=true)

# Architecture
![Architecture](./docs/pics/architecture.png)

According to the state diagram application relies on [the clean architecture](https://www.freecodecamp.org/news/a-quick-introduction-to-clean-architecture-990c014448d2/).

The app relies on the vertical organisation of the source code.
The vertical organisation reflected on the package structure that follows:

- $companyPackage.feature.X.data (holds impl of use cases + schema used to pull data)
- $companyPackage.feature.X.domain (represents plain old Kotlin objects and pure interfaces)
- $companyPackage.feature.X (represents UI layer)
- $companyPackage.lib.Y (represents additional capability used to extend functionality e.g. caching)

The app packages:

- $companyPackage.feature.feed (implementation of Reddit list of Hot feeds)
- $companyPackage.feature.web (implementation of web navigation)
- $companyPackage.lib.okhhtp (utilities used to implement file based caching)
- $companyPackage.ui.theme (defaults from the boilerplate used in compose)

# Libraries
Networking stack
- [Moshi](https://github.com/square/moshi)
- [OkHttp](https://square.github.io/okhttp/)
- [Retrofit](https://square.github.io/retrofit/)

UI stack
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Compose Paging](https://developer.android.com/jetpack/androidx/releases/paging)
- [Custom Chrome Tabs](https://developer.chrome.com/docs/android/custom-tabs/overview/)

Miscellaneous:
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)

Unit Testing
- [Mockk](https://github.com/mockk/mockk)
- [Junit 4](https://junit.org/junit4/)
- [Kluent](https://github.com/MarkusAmshove/Kluent)

# Dev Tools
- Android Studio Arctic Fox | 2020.3.1 Beta 3 (Build #AI-203.7717.56.2031.7395685, built on May 25, 2021)
- [Kotlin 1.4.32](https://github.com/JetBrains/kotlin/releases/tag/v1.4.31)
- [Android Gradle Plugin 7.0.0-beta03](https://developer.android.com/studio/releases/gradle-plugin)
- [Gradle 7.0.2](https://gradle.org/releases/)
