plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    api("com.google.code.gson:gson:2.13.1")
    api("com.github.Konditorei-La-Soleil:gson-kotlin-extensions:v0.2.0")
}

kotlin {
    compilerOptions {
        jvmToolchain(8)
    }
}