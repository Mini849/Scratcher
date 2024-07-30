plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.scratcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.scratcher"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hilt.android)
    implementation(libs.volley)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.runtime.livedata)
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    kapt("com.google.dagger:hilt-compiler:2.50")
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose.v280beta05)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.okhttp)
    implementation(libs.retrofit) // Retrofit dependency
    implementation(libs.converter.gson) // Gson converter

    implementation("androidx.work:work-runtime-ktx:2.8.0")
    implementation(libs.androidx.startup.runtime)


}