import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ydnsa.wificonnectapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ydnsa.wificonnectapp"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt") ,
                "proguard-rules.pro"
                         )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Compose + Material
    implementation("androidx.compose.material:material:1.8.2")
    implementation("androidx.compose.material3:material3:1.3.2")
    implementation("androidx.compose.material:material-icons-core:1.6.1")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

//   Koin
    implementation("io.insert-koin:koin-android:4.0.4")
    implementation("io.insert-koin:koin-androidx-compose:4.0.4")

    // Jetpack Navigation for Compose
    implementation ("androidx.navigation:navigation-compose:2.9.0")

    //gradle
    implementation("io.coil-kt.coil3:coil-compose:3.2.0")

// Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// Mockito
    testImplementation("org.mockito:mockito-core:5.18.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}