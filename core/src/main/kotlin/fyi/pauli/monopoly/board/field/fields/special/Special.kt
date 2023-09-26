package fyi.pauli.monopoly.board.field.fields.special

import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.api.board.field.FieldAppearance
import org.bukkit.Material
import org.bukkit.block.Block

enum class Special(
	override val fieldAppearance: FieldAppearance,
	val amount: Int,
	override val fieldPositions: List<Int>? = null,
	override val fieldPosition: Int? = null
) : Field {
	CHANCE(
		FieldAppearance.PINK, 3, fieldPositions = listOf(7, 22, 36)
	),
	COMMUNITY_CHEST(FieldAppearance.CYAN, 3, fieldPositions = listOf(2, 17, 33)),
	TAX(FieldAppearance.YELLOW, 1, fieldPosition = 4),
	LUXURY_TEX(FieldAppearance.LIGHT_BLUE, 1, fieldPosition = 38);

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (iteration % 4 == 0) fieldAppearance.material else Material.WHITE_CONCRETE
	}

	override val fieldName: String
		get() = name
}