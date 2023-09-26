package fyi.pauli.monopoly

import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.board.field.data.all
import fyi.pauli.monopoly.board.generate.generateField
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs

val testCommand = command("generate") {
	runs {
		val sorted: MutableMap<Int, Field> = mutableMapOf()

		all.forEach { field ->
			field.fieldPositions?.forEach { pos ->
				sorted[pos] = field
			}

			field.fieldPosition?.let { pos -> sorted[pos] = field }
		}

		sorted.toSortedMap(compareBy { it }).forEach(player.location::generateField)
	}
}

val ungenerateCommand = command("unregister") {
	runs {
		Monopoly.INSTANCE.fieldRegistry.fieldContainers.removeAll { fieldContainer ->
			fieldContainer.blocks.forEach {
				it.breakNaturally()
			}
			true
		}
	}
}