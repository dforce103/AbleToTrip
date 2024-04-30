import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "1.9.23"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.hungrybrothers.abletotrip"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hungrybrothers.abletotrip"
        minSdk = 30
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val props =
            Properties().apply {
                file("../secrets.properties").inputStream().use {
                    load(it)
                }
            }
        resValue("string", "kakao_oauth_host", props.getProperty("kakao_oauth_host", "default_oauth_host"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    //    val nav_version = "2.7.7"
    val ktor_version = "2.3.10"
    val lifecycle_version = "2.7.0"

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
    // 카카오 로그인
    implementation("com.kakao.sdk:v2-all:2.20.1") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation("com.kakao.sdk:v2-user:2.20.1") // 카카오 로그인 API 모듈
    implementation("com.kakao.sdk:v2-share:2.20.1") // 카카오톡 공유 API 모듈
    implementation("com.kakao.sdk:v2-talk:2.20.1") // 카카오톡 채널, 카카오톡 소셜, 카카오톡 메시지 API 모듈
    implementation("com.kakao.sdk:v2-friend:2.20.1") // 피커 API 모듈
    implementation("com.kakao.sdk:v2-navi:2.20.1") // 카카오내비 API 모듈
    implementation("com.kakao.sdk:v2-cert:2.20.1") // 카카오톡 인증 서비스 API 모듈

    // 네비게이션
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // 구글 지도 라이브러리
    implementation("com.google.maps.android:maps-compose:2.7.2")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
//    implementation("com.google.maps.android:maps-compose-utils:4.3.5")
//    implementation("com.google.maps.android:maps-compose-widgets:4.3.5")
    //    아이콘아티팩트 추가
    implementation("androidx.compose.material:material-icons-extended")

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-xml:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.compose.runtime:runtime:1.6.6")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.6")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.6.6")
    // 구글 autocomplete
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.23"))
    implementation("com.google.android.libraries.places:places:3.3.0")
    // 레이아웃, 그리기, 입력등 기기와 상호작용할때 필요한 compose UI의 기본적인 구성요소
    implementation("androidx.compose.ui:ui:1.6.6")
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.defaults.properties"

    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*") // Ignore all keys matching the regexp "sdk.*"
}