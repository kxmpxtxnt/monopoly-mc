package fyi.pauli.monopoly.board.field.fields.corner

import fyi.pauli.monopoly.board.field.FieldColor
import fyi.pauli.monopoly.board.field.FieldLike
import org.bukkit.block.Block

enum class Corner(
	override val fieldColor: FieldColor,
	override val fieldPosition: Int?
) : FieldLike {

	GO(FieldColor.RED, 0),
	JAIL(FieldColor.DARK_BLUE, 10),
	FREE_PARKING(FieldColor.LIGHT_BLUE, 20),
	GO_TO_JAIL(FieldColor.GRAY, 30);

	override val blockApply: Block.(Int) -> Unit = {
		type = fieldColor.fieldBlock
	}

	override val width: Int
		get() = 16

	override val fieldName: String
		get() = name
}