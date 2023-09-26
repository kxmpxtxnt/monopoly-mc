package fyi.pauli.monopoly.board.field.fields.street

import fyi.pauli.monopoly.board.field.Field
import fyi.pauli.monopoly.api.board.field.FieldAppearance
import org.bukkit.Material
import org.bukkit.block.Block

enum class Street(
	val fieldAppearance: FieldAppearance,
	val streetPrice: Int,
	override val fieldPosition: Int?
) : Field {

	MEDITERRANEAN_AVENUE(FieldAppearance.PURPLE, 60, 1),
	BALTIC_AVENUE(FieldAppearance.PURPLE, 60, 3),
	ORIENTAL_AVENUE(FieldAppearance.LIGHT_BLUE, 100, 6),
	VERMONT_AVENUE(FieldAppearance.LIGHT_BLUE, 100, 8),
	CONNECTICUT_AVENUE(FieldAppearance.LIGHT_BLUE, 120, 9),
	ST_CHARLES_PLACE(FieldAppearance.PINK, 140, 11),
	STATES_AVENUE(FieldAppearance.PINK, 140, 13),
	VIRGINIA_AVENUE(FieldAppearance.PINK, 160, 14),
	ST_JAMES_PLACE(FieldAppearance.ORANGE, 180, 16),
	TENNESSEE_AVENUE(FieldAppearance.ORANGE, 180, 18),
	NEW_YORK_AVENUE(FieldAppearance.ORANGE, 200, 19),
	KENTUCKY_AVENUE(FieldAppearance.RED, 220, 21),
	INDIANA_AVENUE(FieldAppearance.RED, 240, 23),
	ILLINOIS_AVENUE(FieldAppearance.RED, 260, 24),
	ATLANTIC_AVENUE(FieldAppearance.YELLOW, 260, 26),
	VENTNOR_AVENUE(FieldAppearance.YELLOW, 280, 27),
	MARVIN_GARDENS(FieldAppearance.YELLOW, 300, 29),
	PACIFIC_AVENUE(FieldAppearance.GREEN, 300, 31),
	NORTH_CAROLINA_AVENUE(FieldAppearance.GREEN, 300, 32),
	PENNSYLVANIA_AVENUE(FieldAppearance.GREEN, 320, 34),
	PARK_PLACE(FieldAppearance.DARK_BLUE, 350, 37),
	BOARDWALK(FieldAppearance.DARK_BLUE, 400, 39);

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (iteration > 100) fieldAppearance.material else Material.WHITE_CONCRETE
		}

	override val fieldName: String
		get() = name
}