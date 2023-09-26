package fyi.pauli.monopoly.board.generate

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.board.field.FieldContainer
import fyi.pauli.monopoly.board.field.FieldRegistry
import fyi.pauli.monopoly.board.field.data.field
import fyi.pauli.monopoly.util.minimessage
import net.axay.kspigot.extensions.broadcast
import org.bukkit.Location
import org.bukkit.block.Block

fun Location.generateField(position: Int, field: Field) {
	val registry: FieldRegistry = Monopoly.INSTANCE.fieldRegistry

	val fieldContainer = FieldContainer(field, position)

	if (registry.isRegistered(fieldContainer)) {
		broadcast("<red>Cannot generate field because it is already registered.".minimessage())
		return
	}

	if (!registry.fieldContainerBeforeIsRegistered(fieldContainer) && position != 0) {
		broadcast("<red>Tried to register $position but the container before is not registered.".minimessage())
		return
	}

	val fieldSize = field.height * field.width

	var currentHeight = 0
	var currentWidth = 1

	val side: GenerationSide = GenerationSide.ofPosition(position) ?: error("Invalid generation position.")

	val blocks: MutableList<Block> = mutableListOf()

	for (iteration in 1..fieldSize) {
		val location = when (side) {
			GenerationSide.FIRST -> clone().add(
				(currentWidth + registry.widthBefore(position) + position).toDouble(), 0.0, currentHeight.toDouble()
			)

			GenerationSide.SECOND -> clone().add(
				(currentHeight + registry.widthBefore(10) + 1 + 10).toDouble(),
				0.0,
				(currentWidth + registry.widthBefore(position - 10) - 11 + position).toDouble()
			)


			else -> {
				clone().add(0.0, 20.0, 0.0)
			}
		}.apply {
			val applier = field.blockApply
			block.applier(iteration)
			blocks += block
			block.field = field
		}

		when (iteration) {
			1 -> fieldContainer.first = location
			fieldSize -> fieldContainer.last = location
		}

		if (currentWidth >= field.width) {
			currentHeight++
			currentWidth = 0
		}

		currentWidth++
	}

	fieldContainer.blocks = blocks
	registry.register(fieldContainer)
}