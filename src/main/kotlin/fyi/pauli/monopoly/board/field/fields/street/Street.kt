package fyi.pauli.monopoly.board.field.fields.street

import fyi.pauli.monopoly.board.field.FieldColor
import fyi.pauli.monopoly.board.field.FieldLike
import org.bukkit.Material
import org.bukkit.block.Block

enum class Street(
	override val fieldColor: FieldColor,
	val streetPrice: Int,
	override val fieldPosition: Int?
) : FieldLike {

	MEDITERRANEAN_AVENUE(FieldColor.PURPLE,  60, 1),
	BALTIC_AVENUE(FieldColor.PURPLE,  60, 3),
	ORIENTAL_AVENUE(FieldColor.LIGHT_BLUE,  100, 6),
	VERMONT_AVENUE(FieldColor.LIGHT_BLUE,  100, 8),
	CONNECTICUT_AVENUE(FieldColor.LIGHT_BLUE,  120, 9),
	ST_CHARLES_PLACE(FieldColor.PINK,  140, 11),
	STATES_AVENUE(FieldColor.PINK,  140, 13),
	VIRGINIA_AVENUE(FieldColor.PINK,  160, 14),
	ST_JAMES_PLACE(FieldColor.ORANGE,  180, 16),
	TENNESSEE_AVENUE(FieldColor.ORANGE,  180, 18),
	NEW_YORK_AVENUE(FieldColor.ORANGE,  200, 19),
	KENTUCKY_AVENUE(FieldColor.RED,  220, 21),
	INDIANA_AVENUE(FieldColor.RED,  240, 23),
	ILLINOIS_AVENUE(FieldColor.RED,  260, 24),
	ATLANTIC_AVENUE(FieldColor.YELLOW,  260, 26),
	VENTNOR_AVENUE(FieldColor.YELLOW,  280, 27),
	MARVIN_GARDENS(FieldColor.YELLOW,  300, 29),
	PACIFIC_AVENUE(FieldColor.GREEN,  300, 31),
	NORTH_CAROLINA_AVENUE(FieldColor.GREEN,  300, 32),
	PENNSYLVANIA_AVENUE(FieldColor.GREEN,  320, 34),
	PARK_PLACE(FieldColor.DARK_BLUE,  350, 37),
	BOARDWALK(FieldColor.DARK_BLUE,  400, 40);

	override val blockApply: Block.(Int) -> Unit = { iteration ->
		type = if (iteration > 100) fieldColor.fieldBlock else Material.WHITE_CONCRETE
	}

	override val fieldName: String
		get() = name
}