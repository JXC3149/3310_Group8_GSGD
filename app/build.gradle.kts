plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "uta.edu.a3310_group8_gsgd"
    compileSdk = 34

    defaultConfig {
        applicationId = "uta.edu.a3310_group8_gsgd"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            multiDexEnabled = true
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
    buildFeatures {
        viewBinding = true
        mlModelBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    // Tensorflow Lite dependencies
    implementation("org.tensorflow:tensorflow-lite-task-vision-play-services:0.4.2")
    implementation("com.google.android.gms:play-services-tflite-gpu:16.2.0")
    //implementation("org.tensorflow:tensorflow-lite-support:0.1.0") //causes duplicate dependency issue
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("org.tensorflow:tensorflow-lite:+")
    implementation("org.tensorflow:tensorflow-lite-gpu:+")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-database")
    // Required -- JUnit 4 framework
    testImplementation ("junit:junit:4.12")
    // Optional -- Robolectric environment
    testImplementation ("androidx.test:core:1.0.0")
    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:1.10.19")
}