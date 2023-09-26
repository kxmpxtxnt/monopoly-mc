package fyi.pauli.monopoly.board.field

import fyi.pauli.monopoly.api.board.field.FieldAppearance
import org.bukkit.block.Block

class FieldRegistry {

	val fieldContainers: MutableList<FieldContainer> = mutableListOf()

	fun fieldsByColor(color: FieldAppearance) = fieldContainers.filter { it.field.fieldAppearance == color }

	fun fieldByBlock(block: Block) = fieldContainers.find { it.blocks.contains(block) }

	fun widthBefore(position: Int): Int {
		return fieldContainers
			.filter { position > it.position }
			.sumOf { it.field.width }
	}

	fun heightBefore(position: Int): Int {
		return fieldContainers
			.filter { position > it.position }
			.sumOf { it.field.height }
	}

	fun fieldContainerBefore(fieldContainer: FieldContainer, before: Int = 1): FieldContainer? =
		fieldContainers.find { it.position == (fieldContainer.position - before) }

	fun fieldContainerBeforeIsRegistered(fieldContainer: FieldContainer) =
		fieldContainers.any { it.position == fieldContainer.position - 1 }

	fun isRegistered(fieldContainer: FieldContainer) = fieldContainers.any { it.position == fieldContainer.position }

	fun register(fieldContainer: FieldContainer) {
		if (isRegistered(fieldContainer)) {
			error("Field ${fieldContainer.position} is already in the registry.")
		}

		fieldContainers += fieldContainer
	}
}
