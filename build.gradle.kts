import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "ru.nsu.ccfit"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("br.com.devsrsouza.compose.icons.jetbrains:feather:1.0.0")
                implementation("br.com.devsrsouza.compose.icons.jetbrains:simple-icons:1.0.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "lsbapp"
            packageVersion = "1.0.0"
            linux {
                packageName = "lsbapp"
                iconFile.set(project.file("plum-logo.png"))
                packageVersion = "1.0.0"
            }
            windows{
                packageVersion = "1.0.0"
            }
        }
    }
}
