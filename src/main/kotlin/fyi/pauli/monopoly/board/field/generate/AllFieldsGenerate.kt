package fyi.pauli.monopoly.board.field.generate

import fyi.pauli.monopoly.board.data.all
import org.bukkit.Location

fun Location.generateAllFieldsInLine() {
	var total: Int = 0

	all.forEach { fieldLike ->
		clone().add(total.toDouble(), 0.0, 0.0).generateField(fieldLike, 1)

		total += fieldLike.width + 1
	}
}