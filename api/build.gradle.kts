plugins {
	alias(libs.plugins.jvm)
	alias(libs.plugins.userdev)
	alias(libs.plugins.runpaper)
	alias(libs.plugins.pluginyml)
}

repositories {
	mavenCentral()
}

dependencies {
	paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}