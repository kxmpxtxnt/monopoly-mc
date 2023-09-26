package fyi.pauli.monopoly

import fyi.pauli.monopoly.board.field.FieldMarker
import fyi.pauli.monopoly.board.field.FieldRegistry
import fyi.pauli.monopoly.marker.holders.MarkerCache
import fyi.pauli.monopoly.select.selectField
import net.axay.kspigot.commands.argument
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.suggestList
import net.axay.kspigot.main.KSpigot
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffectType

class Monopoly : KSpigot() {

	lateinit var mainCache: MarkerCache

	val fieldRegistry: FieldRegistry = FieldRegistry()

	val fieldMarker: FieldMarker = FieldMarker(this)

	companion object {
		lateinit var INSTANCE: Monopoly; private set
	}

	override fun startup() {
		INSTANCE = this
		mainCache = MarkerCache(this)
		selectField
		testCommand
		ungenerateCommand
	}

	override fun shutdown() {

	}
}

val Manager by lazy { Monopoly.INSTANCE }
val command = command("potions") {
	argument<String>("effect") {
		this.suggestList { context ->
			if(context.source.entity is Player) {
				(context.source.entity as Player).activePotionEffects.map { it.type.name }
			} else PotionEffectType.values().map { it.name }
		}
	}
}