import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.4.10"
    // __UPDATE_COMPOSE_VERSION_MARKER__
    id("org.jetbrains.compose") version (System.getenv("COMPOSE_TEMPLATE_COMPOSE_VERSION") ?: "0.1.0-build113")
}

repositories {
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:4.4.2")
}

compose.desktop {
    application {
        mainClass = "jetbrains.compose.calculator.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeDesktopCalculator"
        }
    }
}