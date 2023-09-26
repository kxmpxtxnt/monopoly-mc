package fyi.pauli.monopoly.api.board.field

import org.bukkit.block.Block
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox

interface Field {

	val pluginInstance: Plugin

	val fieldName: String
	val fieldAppearance: FieldAppearance

	val width: Int
		get() = 10

	val height: Int
		get() = 16

	val fieldSize: Int
		get() = width * height

	val generateModifier: Block.(Int) -> Unit
		get() = { type = fieldAppearance.material }

	data class Container(
		val field: Field,
		val area: BoundingBox,
	)
}