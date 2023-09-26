package fyi.pauli.monopoly.board.field

import fyi.pauli.monopoly.api.board.field.FieldAppearance
import fyi.pauli.monopoly.util.capitalizeString
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Location
import org.bukkit.block.Block

interface Field {

	val fieldName: String
	val fieldAppearance: FieldAppearance

	val width: Int
		get() = 10

	val height: Int
		get() = 16

	val fieldPosition: Int?
		get() = null

	val fieldPositions: List<Int>?
		get() = null

	val capitalizedName: String
		get() = capitalizeString(fieldName.replace("_", " ").lowercase())

	val coloredName: Component
		get() = MiniMessage.miniMessage().deserialize(capitalizedName).color(fieldAppearance.color)

	val blockApply: Block.(Int) -> Unit
}

class FieldContainer(
	var field: Field,
	var position: Int
) {

	lateinit var last: Location
	lateinit var first: Location

	lateinit var blocks: List<Block>
}