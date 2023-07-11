repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}

plugins {
    id("com.android.application")
    id("kotlin-android")
}

val appId = "test.android.ep"

android {
    namespace = appId
    compileSdk = Version.Android.compileSdk

    defaultConfig {
        applicationId = appId
        minSdk = Version.Android.minSdk
        targetSdk = Version.Android.targetSdk
        versionCode = 1
        versionName = "0.0.$versionCode"
        manifestPlaceholders["appName"] = "@string/app_name"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            isMinifyEnabled = false
            isShrinkResources = false
            manifestPlaceholders["buildType"] = name
        }
    }
}

androidComponents.onVariants { variant ->
    val output = variant.outputs.single()
    check(output is com.android.build.api.variant.impl.VariantOutputImpl)
    val outputFileName = listOf(
        rootProject.name,
        android.defaultConfig.versionName!!,
        variant.name,
        android.defaultConfig.versionCode,
    ).joinToString("-", postfix = ".apk")
    output.outputFileName.set(outputFileName)
    afterEvaluate {
        tasks.getByName<JavaCompile>("compile${variant.name.capitalize()}JavaWithJavac") {
            targetCompatibility = Version.jvmTarget
        }
        tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compile${variant.name.capitalize()}Kotlin") {
            kotlinOptions.jvmTarget = Version.jvmTarget
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
}
