plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "p.vasko.pokemon.compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "p.vasko.pokemon.compose"
        minSdk = 26
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
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    ksp {
//        arg("KOIN_CONFIG_CHECK","true")
//    }
}

dependencies {

    implementation(libs.bundles.core)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.bundles.network)
    implementation(libs.bundles.dataFormat)
    implementation(libs.bundles.jetpackNavigation)
    implementation(libs.bundles.materialUi)

    implementation(libs.material3.android)

    implementation(libs.datastore.preferences)
    implementation(libs.browser)

    implementation(libs.libphonenumber)

    implementation(libs.work.runtime.ktx)

    implementation(libs.lottie.compose)

    implementation (libs.bundles.room)
    ksp (libs.room.compiler)

    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.koin.annotations)
    ksp (libs.koin.ksp.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}