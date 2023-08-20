package fyi.pauli.monopoly

import fyi.pauli.monopoly.board.field.fields.corner.Corner
import fyi.pauli.monopoly.board.field.fields.plant.Plant
import fyi.pauli.monopoly.board.field.fields.special.Special
import fyi.pauli.monopoly.board.field.fields.station.Station
import fyi.pauli.monopoly.board.field.fields.street.Street
import fyi.pauli.monopoly.board.field.generate.generateAllFieldsInLine
import fyi.pauli.monopoly.board.field.generate.generateField
import fyi.pauli.monopoly.board.field.generate.generateSorted
import net.axay.kspigot.commands.*
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material

val generateCommand = command("generate") {

	val all = Street.entries + Station.entries + Plant.entries + Special.entries + Corner.entries

	literal("all") {
		literal("sorted") {
			runs { player.location.generateSorted() }
		}

		runs {
			player.location.generateAllFieldsInLine()
		}
	}

	literal("field") {
		argument<String>("field") {
			suggestListSuspending { all }

			runs {
				val found = all.find { it.name == getArgument("field") }

				if (found == null) {
					player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Field not found"))
					return@runs
				}
				player.location.generateField(found, 1)
			}
		}
	}

	literal("test") {

		runs {
			val width: Int = 10
			val height: Int = 16

			var currentHeight: Double = 0.0
			var currentWidth: Double = 0.0

			for (i in 1..160) {


				currentWidth += 1.0

				if (currentWidth >= width) {
					currentHeight += 1.0
					currentWidth = 0.0
				}
			}
		}
	}
}