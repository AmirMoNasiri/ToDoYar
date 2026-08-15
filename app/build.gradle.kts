plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.plugin)
}

android {
    namespace = "com.amirmonasiri.todoyar"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.amirmonasiri.todoyar"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.lottie.compose)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.navigation.compose)
    implementation(libs.accompanist.permissions)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.datastore.preferences)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.work.runtime)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work.compiler)
    implementation("com.patrykandpatrick.vico:core:2.0.1")
    implementation("com.patrykandpatrick.vico:compose:2.0.1")
    implementation("com.patrykandpatrick.vico:compose-m3:2.0.1")
    implementation("com.adamglin:phosphor-icon:1.0.0")
    implementation("io.github.faridsolgi:persian-date-picker:0.0.14")
    implementation("io.github.faridsolgi:persianDateTime:0.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1-0.6.x-compat")
    ksp(libs.room.compiler)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}