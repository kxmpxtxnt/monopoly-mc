package fyi.pauli.monopoly.board.field

import org.bukkit.block.Block

class FieldRegistry {

	val fields: MutableList<Field> = mutableListOf()

	fun fieldsByColor(color: FieldColor) = fields.filter { it.fieldLike.fieldColor == color }

	fun fieldByBlock(block: Block) = fields.find { it.blocks.contains(block) }

	fun register(field: Field) {
		val existingPositions = mutableSetOf<Int>()

		fun checkAndAddPosition(position: Int) {
			if (!existingPositions.add(position)) {
				error("Field $position is already in the registry.")
			}
		}

		field.fieldLike.fieldPosition?.let { checkAndAddPosition(it) }
		field.fieldLike.fieldPositions?.forEach { checkAndAddPosition(it) }

		fields += field
	}
}