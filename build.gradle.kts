import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.jetbrains.compose") version "1.1.0"
}

group = "com.arsa_fizibilite_app_by_command"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)

    // Module dependencies
    implementation(project(":data"))

    // Cyclone : https://github.com/theapache64/cyclone
    implementation("com.theapache64:cyclone:1.0.0-alpha01")

    // Decompose : Decompose
    val decomposeVersion = "0.5.2"
    implementation("com.arkivanov.decompose:decompose-jvm:$decomposeVersion")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains-jvm:$decomposeVersion")

    // Dagger
    val daggerVersion = "2.41"
    implementation ("com.google.dagger:dagger:$daggerVersion")
    kapt ("com.google.dagger:dagger-compiler:$daggerVersion")

    //KodeinDb
    implementation("org.kodein.db:kodein-db-jvm:0.8.1-beta")
    implementation("org.kodein.db:kodein-db-serializer-kotlinx:0.8.1-beta")
    implementation("org.kodein.db:kodein-leveldb-jni-jvm-windows:0.8.1-beta")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "com.arsa_fizibilite_app_by_command.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "arsa_fizibilite_app_by_command"
            packageVersion = "1.0.0"

            val iconsRoot = project.file("src/main/resources/drawables")

            linux {
                iconFile.set(iconsRoot.resolve("launcher_icons/linux.png"))
            }

            windows {
                iconFile.set(iconsRoot.resolve("launcher_icons/windows.ico"))
            }

            macOS {
                iconFile.set(iconsRoot.resolve("launcher_icons/macos.icns"))
            }

        }
    }
}