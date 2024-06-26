import java.util.Properties

val localProperties = Properties().apply {
    load(File(rootDir, "local.properties").reader())
}

val keystorePath: String  = localProperties.getProperty("keystore")
val storepassword: String = localProperties.getProperty("storepassword")
val keyalias: String = localProperties.getProperty("keyalias")
val keypassword: String = localProperties.getProperty("keypassword")

plugins {
    alias(libs.plugins.androidApplication)
}

android {
    signingConfigs {
        create("wwdev") {
            storeFile = file(keystorePath)
            storePassword = storepassword
            keyAlias = keyalias
            keyPassword = keypassword
        }
    }
    namespace = "com.wwdev.DigitalDash"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wwdev.DigitalDash"
        minSdk = 26
        targetSdk = 34
        versionCode = 10000023
        versionName = "1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("wwdev")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("wwdev")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.wearable)
    implementation(libs.play.services.wearable)
    implementation(libs.swiperefreshlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}