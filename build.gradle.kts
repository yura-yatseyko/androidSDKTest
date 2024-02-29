// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        /*classpath(libs.google.oss.licenses.plugin) {
            exclude(group = "com.google.protobuf")
        }*/
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false

    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.secrets) apply false
}