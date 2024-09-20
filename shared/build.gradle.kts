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
            implementation(libs.kotlinx.datetime)
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

//publishing {
//    publications {
//        maven(MavenPublication) {
//            groupId = 'com.github.geek-atif'
//            artifactId = 'com-atifqamar-customtoast'
//            version = "1.0"
//            pom {
//                description = 'First release'
//            }
//        }
//    }
//    repositories {
//        mavenLocal()
//    }
//}

//publishing{
//    publications{
//        register<MavenPublication>("release"){
//            groupId = "com.jhj0517"
//            artifactId = "koreanchoseongsearch"
//            version = "1.0.0"
//
//            afterEvaluate {
//                from(components["release"])
//            }
//        }
//    }
//
//    repositories {
//        maven {
//            name = "ChoseongSearch"
//            url = uri("https://maven.pkg.github.com/OWNER/REPO_NAME")
//            credentials{
//                username = githubProperties.getProperty("gpr.usr") ?: System.getenv("GITHUB_USERNAME")
//                password = githubProperties.getProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
//            }
//        }
//    }
//}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("release") {
//                from(components.findByName("release"))
//                groupId = "com.github.rootpawel"
//                artifactId = "de.neofonie.messagingmodulekmp"
//                version = "1.0.0"
//                pom {
//                    description = 'First release'
//                }
//            }
//        }
//    }
//}
