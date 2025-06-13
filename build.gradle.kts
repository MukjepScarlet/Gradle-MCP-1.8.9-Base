plugins {
    java
//    kotlin("jvm") version "2.1.21"
    id("com.gradleup.shadow") version "8.3.6" // 9.x requires Java 11+
}

group = "net.example"
version = "1.8.9"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.marcloud.net/")
    maven("https://libraries.minecraft.net/")
}

/** These libraries will be downloaded via `version.json` and excluded from shadowJar */
val gameLibrary: Configuration by configurations.creating

/** These libraries will be included in shadowJar */
val library: Configuration by configurations.creating

configurations.implementation {
    extendsFrom(gameLibrary)
    extendsFrom(library)
}

configurations.shadow {
    extendsFrom(library)
}

tasks.register<JavaExec>("runClient") {
    group = "minecraft"
    description = "Run client"

    mainClass.set("Start")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = file("run")
    jvmArgs = listOf(
        "-Djava.library.path=versions/1.8.9/1.8.9-natives/"
    ) // Add your JVM args!
    args = listOf() // Add your command line args!
}

dependencies {
    gameLibrary("io.netty:netty-all:4.0.23.Final")
    gameLibrary("com.mojang:patchy:1.7.7")
    gameLibrary("net.sf.jopt-simple:jopt-simple:4.6")
    gameLibrary("org.lwjgl:lwjgl:2.9.4-nightly")
    gameLibrary("org.lwjgl:util:2.9.4-nightly")
    gameLibrary("net.java.jinput:jinput:2.0.5")
    gameLibrary("com.mojang:icu4j-core-mojang:51.2")
    gameLibrary("org.apache.httpcomponents:httpclient:4.3.3")
    gameLibrary("org.apache.httpcomponents:httpcore:4.3.2")
    gameLibrary("oshi:oshi-core:1.1")
    gameLibrary("net.java.dev.jna:jna:3.4.0")
    gameLibrary("net.java.dev.jna:platform:3.4.0")
    gameLibrary("net.java.jutils:jutils:1.0.0")
    gameLibrary("commons-logging:commons-logging:1.1.3")
    gameLibrary("org.apache.commons:commons-compress:1.8.1")
    gameLibrary("org.apache.logging.log4j:log4j-api:2.0-beta9")
    gameLibrary("org.apache.logging.log4j:log4j-core:2.0-beta9")
    gameLibrary("tv.twitch:twitch:6.5")
    gameLibrary("com.google.guava:guava:17.0")
    gameLibrary("org.apache.commons:commons-lang3:3.3.2")
    gameLibrary("commons-io:commons-io:2.4")
    gameLibrary("commons-codec:commons-codec:1.9")
    gameLibrary("com.google.code.gson:gson:2.2.4")
    gameLibrary("com.paulscode.sound:libraryjavasound:20101123")
    gameLibrary("com.paulscode.sound:codecwav:20101023")
    gameLibrary("com.paulscode.sound:soundsystem:20120107")
    gameLibrary("com.paulscode.sound:codecjorbis:20101023")
    gameLibrary("com.paulscode.sound:librarylwjglopenal:20100824")
    gameLibrary("com.mojang:authlib:1.5.21")

//    Add your libraries here!
//    library(kotlin("stdlib")) // You should add this if you use Kotlin
}

//kotlin {
//    compilerOptions {
//        jvmToolchain(8)
//    }
//}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.shadowJar {
    configurations = listOf(project.configurations.shadow.get())
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
