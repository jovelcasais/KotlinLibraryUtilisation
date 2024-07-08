plugins {
    alias(libs.plugins.androidApplication)
    //alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-android")
   // id("kotlin-kapt")
    id("kotlinx-serialization")

    id("com.google.devtools.ksp")

}

android {
    namespace = "com.jovelcasais.kotlinlibraryutilisation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jovelcasais.kotlinlibraryutilisation"
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
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
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Networking
    implementation(libs.retrofit2)
    implementation(libs.okhttp)
    implementation(libs.okhttpInterceptor)

    // Coroutine
    implementation(libs.coroutines)
    implementation(libs.coroutinesCore)

    implementation(libs.hilt)
    implementation(libs.moshi)

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)

    implementation(libs.kotlinSerialization)
    implementation(libs.lifecycleViewModel) // di for viewmodel

    ksp(libs.roomCompiler)
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.material:material")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("com.google.code.gson:gson:2.8.9")

    implementation("com.github.jovelcasais:MyUIComponents:1.3")

}
