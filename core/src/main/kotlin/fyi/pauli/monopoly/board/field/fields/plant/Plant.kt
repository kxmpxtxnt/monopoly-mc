package fyi.pauli.monopoly.board.field.fields.plant

import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.api.board.field.FieldAppearance
import org.bukkit.Material
import org.bukkit.block.Block

enum class Plant(
	override val fieldAppearance: FieldAppearance,
	override val fieldPosition: Int?
) : Field {

	ELECTRICITY_PLANT(FieldAppearance.YELLOW, 12),
	WATER_PLANT(FieldAppearance.DARK_BLUE, 28);

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (listOf(
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 20, 21, 30, 31, 40, 41,
				50, 51, 60, 61, 70, 71, 80, 81, 90, 91, 100, 101, 110, 111,
				120, 121, 130, 131, 140, 141, 150, 151, 152, 153, 154, 155,
				156, 157, 158, 159, 160
			).contains(iteration)
		) fieldAppearance.material else Material.WHITE_CONCRETE
	}

	override val fieldName: String
		get() = name
}