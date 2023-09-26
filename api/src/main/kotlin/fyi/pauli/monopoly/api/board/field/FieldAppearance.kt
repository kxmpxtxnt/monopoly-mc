package fyi.pauli.monopoly.api.board.field

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material

data class FieldAppearance(
	val color: TextColor,
	val material: Material
) {

	object FieldAppearances {
		val BLACK = FieldAppearance(NamedTextColor.BLACK, Material.BLACK_CONCRETE)
		val PURPLE = FieldAppearance(NamedTextColor.DARK_PURPLE, Material.PURPLE_CONCRETE)
		val LIGHT_BLUE = FieldAppearance(NamedTextColor.BLUE, Material.LIGHT_BLUE_CONCRETE)
		val PINK = FieldAppearance(NamedTextColor.LIGHT_PURPLE, Material.PINK_CONCRETE)
		val ORANGE = FieldAppearance(NamedTextColor.GOLD, Material.ORANGE_CONCRETE)
		val RED = FieldAppearance(NamedTextColor.RED, Material.RED_CONCRETE)
		val YELLOW = FieldAppearance(NamedTextColor.YELLOW, Material.YELLOW_CONCRETE)
		val GREEN = FieldAppearance(NamedTextColor.DARK_GREEN, Material.GREEN_CONCRETE)
		val DARK_BLUE = FieldAppearance(NamedTextColor.DARK_BLUE, Material.BLUE_CONCRETE)
		val GRAY = FieldAppearance(NamedTextColor.GRAY, Material.LIGHT_GRAY_CONCRETE)
		val CYAN = FieldAppearance(TextColor.color(0, 255, 255), Material.CYAN_CONCRETE)
	}
}