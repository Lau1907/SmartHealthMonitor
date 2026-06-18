plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "mx.utng.smarthealthmonitor.wear"
    compileSdk = 35

    defaultConfig {
        applicationId = "mx.utng.smarthealthmonitor.wear"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures { compose = true }
}

dependencies {
    implementation(libs.play.services.wearable)
    implementation("androidx.health:health-services-client:1.1.0-alpha03")
    implementation("com.google.guava:guava:33.0.0-android")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.7.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
    implementation("androidx.wear.compose:compose-material:1.3.1")
    implementation("androidx.wear.compose:compose-foundation:1.3.1")
    implementation("androidx.wear.compose:compose-navigation:1.3.1")
    implementation("com.google.android.horologist:horologist-compose-layout:0.6.17")
    implementation("com.google.android.horologist:horologist-compose-material:0.6.17")
    implementation("androidx.wear.watchface:watchface:1.2.1")
    implementation("androidx.wear.watchface:watchface-complications-rendering:1.2.1")
    implementation("androidx.wear.watchface:watchface-style:1.2.1")
}