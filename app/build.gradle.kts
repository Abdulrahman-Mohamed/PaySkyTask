plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")


}

android {
    namespace = "com.example.payskytask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.payskytask"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}
dependencies {

    dependencies {
        // Core
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")

        // Navigation
        implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
        implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
        implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.7")

        // Legacy Support
        implementation("androidx.legacy:legacy-support-v4:1.0.0")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:okhttp:4.9.0")
        val lifecycle_version = "2.8.0"
        val arch_version = "2.2.0"
        // Room
        val room_version = "2.6.1"
        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")
        testImplementation("androidx.room:room-testing:$room_version")
        implementation("androidx.room:room-ktx:$room_version")

        // Lifecycle
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
//        implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

        implementation("androidx.core:core-ktx:1.13.1")

        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

        // Annotation processor
        //noinspection LifecycleAnnotationProcessorWithJava8
        kapt("androidx.lifecycle:lifecycle-compiler:2.2.0")
        // alternately - if using Java8, use the following instead of lifecycle-compiler
        implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

        // Gson
        implementation("com.google.code.gson:gson:2.10")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

        // Hilt
        implementation("com.google.dagger:hilt-android:2.51.1")
        kapt("com.google.dagger:hilt-compiler:2.51.1")
//        kapt("androidx.hilt:hilt-compiler:1.2.0")
//        kapt ("com.google.dagger:hilt-android-compiler:2.51.1")

//        implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
        implementation("androidx.room:room-runtime:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")
        // Work Manager
        val work_version = "2.9.0"
        implementation("androidx.work:work-runtime:$work_version")
        implementation("androidx.work:work-runtime-ktx:$work_version")

        // Lottie
//        def lottieVersion = "3.4.0"
//        implementation("com.airbnb.android:lottie:$lottieVersion")
//
//        // Glide
//        implementation("com.github.bumptech.glide:glide:4.12.0")
//        annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
//
//        // TouchImageView
//        implementation("com.github.MikeOrtiz:TouchImageView:1.4.1")
    }

}