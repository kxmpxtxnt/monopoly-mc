package fyi.pauli.monopoly.board.field.fields.station

import fyi.pauli.monopoly.board.field.FieldColor
import fyi.pauli.monopoly.board.field.FieldLike
import net.minecraft.core.Direction
import org.bukkit.Material
import org.bukkit.block.Block

enum class Station(
	val direction: Direction,
	override val fieldPosition: Int?
) : FieldLike {

	READING_RAILROAD(Direction.NORTH, 5),
	PENNSYLVANIA_RAILROAD(Direction.EAST, 15),
	B_AND_Q_RAILROAD(Direction.SOUTH, 25),
	SHORT_LINE(Direction.WEST, 35)
	;

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (listOf(75, 76, 85, 86).contains(iteration)) fieldColor.fieldBlock else Material.WHITE_CONCRETE
	}

	val price: Int = 200

	override val fieldColor: FieldColor
		get() = FieldColor.BLACK

	override val fieldName: String
		get() = name
}