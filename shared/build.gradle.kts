@file:Suppress("DEPRECATION")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release")
        //publishAllLibraryVariants()
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "de.neofonie.messagingmodulekmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/**"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.foundation.layout.android)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components.findByName("release"))
            groupId = "com.github.rootpawel"
            artifactId = "de.neofonie.messagingmodulekmp"
            version = "1.0.13"
        }
    }
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("release") {
//                from(components.findByName("release"))
//                groupId = "com.github.rootpawel"
//                artifactId = "de.neofonie.messagingmodulekmp"
//                version = "1.0.0"
//            }
//        }
//    }
//}
