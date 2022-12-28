// Gradle buildscript for a Velocity plugin made with Kotlin.
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // The Kotlin/JVM Gradle plugin. Needed for any Kotlin project targeting the JVM.
    alias(libs.plugins.kotlin.jvm)
    // kapt annotation processing. Needed to generate the velocity-plugin.json file.
    alias(libs.plugins.kotlin.kapt)

    // The shadow gradle plugin. Allows us to bundle the Kotlin standard library in our project.
    alias(libs.plugins.shadow)
}

// The maven group and version of your plugin.
group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    // The maven central repository. Many additional libraries are found here.
    mavenCentral()

    // The PaperMC maven repository. Needed to use the Velocity API.
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    /*
    * The Velocity API and annotation processor.
    * It's obvious why these are needed.
    */
    compileOnly(libs.velocity.api)
    kapt(libs.velocity.api)

    // Shadow the kotlin standard library.
    shadow(kotlin("stdlib"))
}

tasks.withType<KotlinCompile> {
    // Use Java 11.
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    /*
    * Removes the "-all" from the end of the plugin JAR.
    * You can safely remove this line if you'd prefer, or change the value.
    */
    archiveClassifier.set("")

    configurations {
        // Include all dependencies from the "shadow" configuration.
        project.configurations.shadow
    }

    // Relocate Kotlin to avoid conflicts.
    relocate("kotlin", "${project.group}.shadow.kotlin")
    relocate("kotlinx", "${project.group}.shadow.kotlinx")
}