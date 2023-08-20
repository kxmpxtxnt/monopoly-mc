package fyi.pauli.monopoly.board.field

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Location
import org.bukkit.block.Block
import java.util.*

interface FieldLike {

	val fieldName: String
	val fieldColor: FieldColor

	val width: Int
		get() = 10

	val height: Int
		get() =   16

	val fieldPosition: Int?
		get() = null

	val fieldPositions: List<Int>?
		get() = null

	val hasMultiplePositions: Boolean
		get() = fieldPositions == null && fieldPosition != null

	val beautifiedName: String
		get() = capitalize(fieldName.replace("_", " ").lowercase())

	val coloredName: Component
		get() = MiniMessage.miniMessage().deserialize(beautifiedName).color(fieldColor.namedColor)

	val blockApply: Block.(Int) -> Unit

	private fun capitalize(str: String): String {
		return str.trim().split("\\s+".toRegex()).joinToString(" ") { string ->
			string.replaceFirstChar {
				if (it.isLowerCase()) it.titlecase(
					Locale.getDefault()
				) else it.toString()
			}
		}
	}
}

class Field(
	var fieldLike: FieldLike
) {

	lateinit var last: Location
	lateinit var first: Location
	lateinit var blocks: List<Block>
}