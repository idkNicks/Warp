import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "io.github.idknicks.warp"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("http://idkNicks.xyz:8081/repository/maven-public/")
        isAllowInsecureProtocol = true
    }
}

dependencies {

    /** CORE */
    compileOnly("com.github:nicklib:1.1.7")

    /** BUKKIT API */
    compileOnly("org.bukkit.spigotmc:spigot-api:1.19.2-R0.1")

    /** OTHERS */
    implementation("org.jetbrains:annotations:23.0.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}


/** BUILD SETTINGS */
buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}

apply(plugin = "kotlin")
apply(plugin = "com.github.johnrengelman.shadow")

tasks.withType<Jar> {
    archiveFileName.set("${rootProject.name}.jar")
}

val projectMainClass = "AppKt"
tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
            "Main-Class" to projectMainClass
        ))
    }
}

application {
    mainClass.set(projectMainClass)
}