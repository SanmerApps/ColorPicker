plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.compose.gradle)
    compileOnly(libs.kotlin.gradle)
}

gradlePlugin {
    plugins {
        register("self.application") {
            id = "self.application"
            implementationClass = "ApplicationConventionPlugin"
        }

        register("self.compose") {
            id = "self.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}