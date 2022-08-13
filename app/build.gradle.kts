plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.pbarthuel.bodywellbeing"
        minSdk = 24
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = LibVersion.composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        isAbortOnError = false
    }
}

object LibVersion {
    const val composeVersion = "1.1.0-rc01"
    const val hiltVersion = "2.38.1"
    const val roomVersion = "2.4.0"
    const val accompanistVersion = "0.22.0-rc"
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:${LibVersion.composeVersion}")
    implementation("androidx.compose.material:material:${LibVersion.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${LibVersion.composeVersion}")
    implementation("androidx.compose.runtime:runtime-livedata:${LibVersion.composeVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

    // Accompanist
    implementation("com.google.accompanist:accompanist-insets:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-pager:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-pager-indicators:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-permissions:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-navigation-animation:${LibVersion.accompanistVersion}")
    implementation("com.google.accompanist:accompanist-navigation-material:${LibVersion.accompanistVersion}")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:29.0.3"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.0.1")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation(platform("com.google.firebase:firebase-bom:30.3.1"))
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Room
    implementation("androidx.room:room-runtime:${LibVersion.roomVersion}")
    kapt("androidx.room:room-compiler:${LibVersion.roomVersion}")
    implementation("androidx.room:room-ktx:${LibVersion.roomVersion}")
    testImplementation("androidx.room:room-testing:${LibVersion.roomVersion}")

    // Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.1")
    implementation("com.google.code.gson:gson:2.8.6")

    // Hilt
    implementation("com.google.dagger:hilt-android:${LibVersion.hiltVersion}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-compiler:${LibVersion.hiltVersion}")

    testImplementation("junit:junit:4.13.2")
    //Mockk
    androidTestImplementation("io.mockk:mockk-android:1.12.2")
    androidTestImplementation("io.mockk:mockk-agent-jvm:1.12.2")
    // For instrumentation tests
    testImplementation("com.google.dagger:hilt-android-testing:2.37")
    kaptTest("com.google.dagger:hilt-android-compiler:${LibVersion.hiltVersion}")
    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.37")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${LibVersion.hiltVersion}")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${LibVersion.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${LibVersion.composeVersion}")
}
