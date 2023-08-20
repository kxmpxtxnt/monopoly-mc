package fyi.pauli.monopoly.board.field.fields.special

import fyi.pauli.monopoly.board.field.FieldColor
import fyi.pauli.monopoly.board.field.FieldLike
import org.bukkit.Material
import org.bukkit.block.Block

enum class Special(
	override val fieldColor: FieldColor,
	val amount: Int,
	override val fieldPositions: List<Int>? = null,
	override val fieldPosition: Int? = null
) : FieldLike {

	CHANCE(FieldColor.PINK, 3, fieldPositions = listOf(2, 17, 33)),
	COMMUNITY_CHEST(FieldColor.CYAN, 3, fieldPositions = listOf(7, 22, 36)),
	TAX(FieldColor.YELLOW, 1, fieldPosition = 4),
	LUXURY_TEX(FieldColor.LIGHT_BLUE, 1, fieldPosition = 39);

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (iteration % 4 == 0) fieldColor.fieldBlock else Material.WHITE_CONCRETE
	}

	override val fieldName: String
		get() = name
}