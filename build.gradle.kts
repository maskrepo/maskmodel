val quarkusVersion: String = "1.8.0.Final"

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    `maven-publish`
    id("jacoco")
}

group = "fr.convergence.proddoc.lib"
version = "1.1.4-SNAPSHOT"

val myMavenRepoUser = "myMavenRepo"
val myMavenRepoPassword = "mask"

repositories {
    mavenLocal()
    maven {
        url = uri("https://mymavenrepo.com/repo/OYRB63ZK3HSrWJfc2RIB/")
        credentials {
            username = myMavenRepoUser
            password = myMavenRepoPassword
        }
    }
    mavenCentral()
}

publishing {
    repositories {
        maven {
            url = uri("https://mymavenrepo.com/repo/ah37AFHxnt3Fln1mwTvi/")
            credentials {
                username = myMavenRepoUser
                password = myMavenRepoPassword
            }
        }
        mavenLocal()
    }

    publications {
        create<MavenPublication>("mask-model") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")

    implementation("org.apache.kafka:kafka-clients:2.6.0")
    implementation("org.reflections:reflections:0.9.12")
    implementation("io.debezium:debezium-core:1.1.2.Final")
    implementation("io.quarkus:quarkus-resteasy:$quarkusVersion")
    implementation("io.quarkus:quarkus-resteasy-jackson:$quarkusVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.assertj:assertj-core:3.12.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}