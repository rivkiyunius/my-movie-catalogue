plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

apply {
    from("../shared_dependencies.gradle")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        buildConfigField("String", "BASE_URL", """"https://api.themoviedb.org/3/"""")
        buildConfigField("String", "DOMAIN_NAME", """"api.themoviedb.org"""")
        buildConfigField("String", "MOVIE_KEY", """"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTVhY2Q4MGQ3MjI2NmUzMjkwMDg2ZmYzZTI4NjU5MiIsInN1YiI6IjVhYjQ1ZjRjYzNhMzY4NjE2MzAxNjM1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.I0mFW1ae1EnomFVf37gtBOUCRaeDPhvKCRftOWkDaVE"""")
        buildConfigField("String", "CERTIFICATE_KEY", """"sha256/5VLcahb6x4EvvFrCF2TePZulWqrLHS2jCg9Ywv6JHog="""")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles.add(file("consumer-rules.pro"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":domain"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("net.zetetic:android-database-sqlcipher:4.4.0")
    implementation("androidx.sqlite:sqlite-ktx:2.4.0")
}

kapt {
    correctErrorTypes = true
}