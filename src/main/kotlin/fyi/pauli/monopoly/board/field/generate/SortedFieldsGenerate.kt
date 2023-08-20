package fyi.pauli.monopoly.board.field.generate

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.board.data.all
import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.board.field.FieldLike
import fyi.pauli.monopoly.board.field.FieldRegistry
import net.axay.kspigot.extensions.broadcast
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Location

fun Location.generateSorted() {

	val sortedFields: MutableMap<Int, FieldLike> = mutableMapOf()

	all.forEach { field ->
		if(field.fieldPosition == null && field.fieldPositions != null) {
			field.fieldPositions?.forEach { position ->
				sortedFields[position] = field
			}
		}

		if(field.fieldPosition != null ) sortedFields[field.fieldPosition!!] = field
	}

	var total: Int = 0

	sortedFields.toSortedMap(compareByDescending<Int> { it }.reversed()).forEach { (position, field) ->
		broadcast(MiniMessage.miniMessage().deserialize("<rainbow>Position $position -> ").append(field.coloredName))
		clone().add(total.toDouble(), 0.0, 0.0).generateField(field, position)
		total += field.width + 1
	}
}