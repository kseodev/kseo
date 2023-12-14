plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.github.kseodev"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("io.github.reactivecircus.cache4k:cache4k:0.12.0")
    implementation("com.github.minndevelopment:jda-ktx:9370cb1")
    implementation("com.github.discord-jda:JDA:v5.0.0-beta.18")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("com.github.Chew:JDA-Chewtils:a86d4de")
}

kotlin {
    jvmToolchain(21)
}