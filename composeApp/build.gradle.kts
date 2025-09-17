import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
            }
        }
        val composeMain by creating {
            kotlin.srcDir("composeMain/kotlin")
            dependencies {
                implementation(libs.math.mxparser)
            }
            dependsOn(commonMain.get())
        }
        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
            dependsOn(composeMain)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        val desktopMain by creating {
            kotlin {
                srcDir("desktopMain/kotlin")
            }
            dependsOn(composeMain)
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
            }
        }

        jvmMain.get().dependsOn(desktopMain)
    }
}


android {
    namespace = "jetbrains.compose.calculator"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "jetbrains.compose.calculator"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "jetbrains.compose.calculator.MainKt"

        nativeDistributions { // see https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-native-distribution.html
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Calculator"
            packageVersion = "1.0.1"

            macOS {
                bundleID = "jetbrains.compose.calculator"
            }
            windows {
                dirChooser = true
                perUserInstall = true
                menuGroup = "Calculator"
                upgradeUuid = "55d404e2-a944-4a3c-b42d-6f391656d517" // Project specific value
                iconFile.set(project.file("src/desktopMain/resources/calculator.ico"))
            }
            linux {
                menuGroup = "Calculator"
                iconFile.set(project.file("src/commonMain/composeResources/drawable/calculator.png"))
            }

        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "composeResources"
    generateResClass = auto
}
