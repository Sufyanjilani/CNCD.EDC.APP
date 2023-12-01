// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
}

buildscript{

    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.15.1")
    }
}