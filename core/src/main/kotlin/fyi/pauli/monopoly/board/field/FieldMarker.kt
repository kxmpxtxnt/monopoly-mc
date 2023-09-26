package fyi.pauli.monopoly.board.field

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.marker.markers.CubicMarker
import net.axay.kspigot.extensions.bukkit.bukkitColor
import org.bukkit.entity.Player
import org.bukkit.util.BoundingBox

class FieldMarker(val instance: Monopoly) {

	lateinit var globalMarker: CubicMarker

	fun createGlobalMarker(fieldContainer: FieldContainer) {
		globalMarker = CubicMarker(
			null,
			instance.mainCache,
			fieldContainer.first.world,
			BoundingBox.of(
				fieldContainer.first.toCenterLocation(),
				fieldContainer.last.toCenterLocation()
			).expand(0.4)
		).apply {
			isGlobalVisibility = true
			glowColor = fieldContainer.field.fieldAppearance.color.bukkitColor
		}

		globalMarker.mark()
	}

	fun move(fieldContainer: FieldContainer) = globalMarker.apply {
		area = BoundingBox.of(
			fieldContainer.first.toCenterLocation(),
			fieldContainer.last.toCenterLocation()
		).expand(0.4)

		updateMarker()
	}

	fun remove() = globalMarker.apply {
		isGlobalVisibility = false
		updateMarker()
	}

	class PlayerMarker(
		val player: Player,
		val marker: CubicMarker,
		var currentFieldContainer: FieldContainer,
		var isVisible: Boolean = true
	) {

		init {
			marker.glowColor = currentFieldContainer.field.fieldAppearance.color.bukkitColor
			marker.mark()
		}

		fun update(fieldContainer: FieldContainer) {
			currentFieldContainer = fieldContainer
			marker.area =
				BoundingBox.of(fieldContainer.first.toCenterLocation(), fieldContainer.last.toCenterLocation()).expand(0.4)
			marker.glowColor = fieldContainer.field.fieldAppearance.color.bukkitColor
			marker.isGlobalVisibility = false
			marker.addPlayer(player)
			marker.updateMarker()
			isVisible = true
		}

		fun disableMarker() {
			marker.removeMarker(false)
			isVisible = false
		}
	}

	fun createMarker(player: Player, fieldContainer: FieldContainer): PlayerMarker {
		val marker = CubicMarker(
			player,
			instance.mainCache,
			player.world,
			BoundingBox.of(
				fieldContainer.first.toCenterLocation(),
				fieldContainer.last.toCenterLocation()
			).expand(0.4)
		)

		val playerMarker = PlayerMarker(player, marker, fieldContainer)
		playerMarkers += playerMarker
		return playerMarker
	}

	val playerMarkers: MutableList<PlayerMarker> = mutableListOf()

}

var Player.marker: FieldMarker.PlayerMarker?
	get() = Monopoly.INSTANCE.fieldMarker.playerMarkers.find { it.player == player }
	set(marker) {
		if (marker == null) {
			Monopoly.INSTANCE.fieldMarker.playerMarkers.removeIf { it.player == player }
			return
		}

		Monopoly.INSTANCE.fieldMarker.playerMarkers += marker
	}