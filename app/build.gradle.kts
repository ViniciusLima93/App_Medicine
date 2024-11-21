import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") version "4.4.2"
}

android {
    namespace = "com.example.codedoctors"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.codedoctors"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {



    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:21.0.0")
    implementation("com.google.firebase:firebase-firestore:25.1.1")
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.gms:google-services:4.4.2")

    dependencies {

        val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
        implementation(composeBom)
        androidTestImplementation(composeBom)

        // Choose one of the following:
        // Material Design 3
        implementation("androidx.compose.material3:material3")
        // or Material Design 2
        implementation("androidx.compose.material:material")
        // or skip Material Design and build directly on top of foundational components
        implementation("androidx.compose.foundation:foundation")
        // or only import the main APIs for the underlying toolkit systems,
        // such as input and measurement/layout
        implementation("androidx.compose.ui:ui")

        // Android Studio Preview support
        implementation("androidx.compose.ui:ui-tooling-preview")
        debugImplementation("androidx.compose.ui:ui-tooling")

        // UI Tests
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // Optional - Included automatically by material, only add when you need
        // the icons but not the material library (e.g. when using Material3 or a
        // custom design system based on Foundation)
        implementation("androidx.compose.material:material-icons-core")
        // Optional - Add full set of material icons
        implementation("androidx.compose.material:material-icons-extended")
        // Optional - Add window size utils
        implementation("androidx.compose.material3.adaptive:adaptive")

        // Optional - Integration with activities
        implementation("androidx.activity:activity-compose:1.9.2")
        // Optional - Integration with ViewModels
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
        // Optional - Integration with LiveData
        implementation("androidx.compose.runtime:runtime-livedata")
        // Optional - Integration with RxJava
        implementation("androidx.compose.runtime:runtime-rxjava2")
        val fragment_version = "1.8.5"

        // Java language implementation
        implementation("androidx.fragment:fragment:$fragment_version")
        // Kotlin
        implementation("androidx.fragment:fragment-ktx:$fragment_version")

        //Navigation
        implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
        implementation("androidx.navigation:navigation-ui-ktx:2.6.0")


    }

}