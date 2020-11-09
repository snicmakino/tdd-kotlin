import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.shinichi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
sourceSets {
    getByName("main").java.srcDirs("src/main/money")
    getByName("test").java.srcDirs("src/test/money")
}
dependencies {
    implementation("io.kotest:kotest-runner-junit5-jvm:4.1.1")
    implementation("io.kotest:kotest-runner-junit5-jvm:4.1.1")
    testImplementation("io.kotest:kotest-runner-junit5:4.3.0") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:4.3.0") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:4.3.0") // for kotest property test
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}
tasks.withType<Test> {
    useJUnitPlatform()
}
