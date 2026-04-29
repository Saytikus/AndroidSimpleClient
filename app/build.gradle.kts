plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

android {
    namespace = "ru.saytikus.androidsimpleclient"

    compileSdk = 36

    defaultConfig {
        applicationId = "ru.saytikus.androidsimpleclient"
        minSdk = 29
        targetSdk = 36

        versionCode = 8
        versionName = "0.5.0"
        val versionDatetime = "23:48:00 29.04.2026"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    implementation(libs.koin.android)
    ksp(libs.koin.ksp.compiler)

    implementation(libs.androidx.room.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.activity.compose)

    implementation(libs.ksafe.core)

    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":data"))
}