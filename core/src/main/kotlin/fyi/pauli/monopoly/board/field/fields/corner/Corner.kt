package fyi.pauli.monopoly.board.field.fields.corner

import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.api.board.field.FieldAppearance
import org.bukkit.block.Block

enum class Corner(
	override val fieldAppearance: FieldAppearance,
	override val fieldPosition: Int?
) : Field {

	GO(FieldAppearance.RED, 0),
	JAIL(FieldAppearance.DARK_BLUE, 10),
	FREE_PARKING(FieldAppearance.LIGHT_BLUE, 20),
	GO_TO_JAIL(FieldAppearance.GRAY, 30);

	override val blockApply: Block.(Int) -> Unit = { _ ->
		type = fieldAppearance.material
	}

	override val width: Int
		get() = 16

	override val fieldName: String
		get() = name
}