package fyi.pauli.monopoly.board.field.generate

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.board.data.field
import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.board.field.FieldLike
import fyi.pauli.monopoly.board.field.FieldRegistry
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block

fun Location.generateField(fieldLike: FieldLike, position: Int) {
	var currentHeight: Double = 0.0
	var currentWidth: Double = 0.0

	val width = fieldLike.width
	val height = fieldLike.height

	val registry: FieldRegistry = Monopoly.INSTANCE.fieldRegistry

	val field: Field = Field(fieldLike)
	val blocks: MutableList<Block> = mutableListOf()

	val fieldSize: Int = width * height

	for (iteration in 1..fieldSize) {
		val location = when(position) {
			in 0..9 -> clone().add(currentWidth, 0.0, currentHeight)

			//in 10..19 -> clone().add(0.0, currentWidth, currentHeight)

			//in 20..29 -> clone().add(-currentWidth-160, 0.0, -currentHeight+36)

			else -> clone().add(currentWidth, 100.0, currentHeight)
		}

		blocks += location.block

		when(iteration) {
			1 -> field.first = location
			fieldSize -> field.last = location
		}

		val applier = fieldLike.blockApply

		location.block.applier(iteration)
		location.block.field = fieldLike

		currentWidth += 1.0

		if(currentWidth >= width) {
			currentHeight += 1.0
			currentWidth = 0.0
		}
	}

	field.blocks = blocks
	registry.register(field)
}