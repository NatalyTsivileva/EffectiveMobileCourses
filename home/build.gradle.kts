plugins {
    alias(libs.plugins.android.library)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "ru.compose.tsivileva.effectivemobilecourses.home"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(project(":core"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.koin.android)

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")

}