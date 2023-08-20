import net.minecrell.pluginyml.paper.PaperPluginDescription

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
	paperLibrary(libs.kspigot)

	implementation(platform(libs.bom))
	compileOnly(libs.bundles.fawe)
}

paper {
	name = "monopoly-mc"
	author = "kxmpxtxnt"
	version = "1.0"
	apiVersion = "1.20"

	foliaSupported = true

	main = "fyi.pauli.monopoly.Monopoly"

	loader = "fyi.pauli.monopoly.MonopolyLoader"

	serverDependencies {
		register("FastAsyncWorldEdit") {
			required = true
			load = PaperPluginDescription.RelativeLoadOrder.BEFORE
		}
	}

	generateLibrariesJson = true
}