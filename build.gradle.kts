import org.gradle.api.tasks.wrapper.Wrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version = "1.3.41"
val junit_jupiter_version = "5.5.0"
val assertk_version = "0.17"

plugins {
    kotlin("jvm") version "1.3.41"
}

repositories {
    mavenCentral()
}

dependencies {
    testCompile("org.junit.jupiter:junit-jupiter-api:$junit_jupiter_version")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junit_jupiter_version")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junit_jupiter_version")

    testCompile("com.willowtreeapps.assertk:assertk-jvm:$assertk_version")

    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    compile(kotlin("stdlib-jdk8"))
}

tasks.wrapper {
    gradleVersion = "5.5.1"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}