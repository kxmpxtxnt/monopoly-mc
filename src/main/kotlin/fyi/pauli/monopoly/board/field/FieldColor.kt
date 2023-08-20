package fyi.pauli.monopoly.board.field

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Color
import org.bukkit.Material

enum class FieldColor(
	val namedColor: TextColor,
	val fieldBlock: Material
) {

	BLACK(NamedTextColor.BLACK, Material.BLACK_CONCRETE),
	PURPLE(NamedTextColor.DARK_PURPLE, Material.PURPLE_CONCRETE),
	LIGHT_BLUE(NamedTextColor.BLUE, Material.LIGHT_BLUE_CONCRETE),
	PINK(NamedTextColor.LIGHT_PURPLE, Material.PINK_CONCRETE),
	ORANGE(NamedTextColor.GOLD, Material.ORANGE_CONCRETE),
	RED(NamedTextColor.RED, Material.RED_CONCRETE),
	YELLOW(NamedTextColor.YELLOW, Material.YELLOW_CONCRETE),
	GREEN(NamedTextColor.DARK_GREEN, Material.GREEN_CONCRETE),
	DARK_BLUE(NamedTextColor.DARK_BLUE, Material.BLUE_CONCRETE),
	GRAY(NamedTextColor.GRAY, Material.LIGHT_GRAY_CONCRETE),
	CYAN(TextColor.color(0, 255, 255), Material.CYAN_CONCRETE)
}