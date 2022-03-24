
plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.10"
}

group = "com.arsa_fizibilite_app_by_command"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Jsoup
    val jsoupVersion = "1.14.3"
    implementation("org.jsoup:jsoup:$jsoupVersion")

    // Selenium
    val seleniumVersion = "4.1.2"
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    implementation("io.github.bonigarcia:webdrivermanager:5.1.0")

    // Coroutines
    val coroutinesVersion = "1.6.0"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Dagger
    val daggerVersion = "2.41"
    api("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation("org.kodein.db:kodein-db:0.8.1-beta")
    implementation ("org.kodein.db:kodein-leveldb-jni-jvm-windows:0.8.1-beta")
    implementation("org.kodein.db:kodein-db-serializer-kotlinx:0.8.1-beta")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // Arbor : Like Timber, just different.
    api("com.ToxicBakery.logging:arbor-jvm:1.34.109")
}




