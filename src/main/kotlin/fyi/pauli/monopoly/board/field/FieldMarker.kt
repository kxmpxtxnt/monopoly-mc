package fyi.pauli.monopoly.board.field

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.marker.markers.CubicMarker
import net.axay.kspigot.extensions.bukkit.bukkitColor
import org.bukkit.entity.Player
import org.bukkit.util.BoundingBox

class FieldMarker(val instance: Monopoly) {

	class PlayerMarker(
		val player: Player,
		val marker: CubicMarker,
		var currentField: Field,
		var isVisible: Boolean = true
	) {

		init {
			marker.glowColor = currentField.fieldLike.fieldColor.namedColor.bukkitColor
			marker.mark()
		}

		fun update(field: Field) {
			currentField = field
			marker.area = BoundingBox.of(field.first.toCenterLocation(), field.last.toCenterLocation()).expand(0.4)
			marker.glowColor = field.fieldLike.fieldColor.namedColor.bukkitColor
			marker.isGlobalVisibility = false
			marker.addPlayer(player)
			marker.updateMarker()
			isVisible = true
		}

		fun disableMarker(){
			marker.removeMarker(false)
			isVisible = false
		}
	}

	fun createMarker(player: Player, field: Field): PlayerMarker {
		val marker = CubicMarker(
			player,
			instance.mainCache,
			player.world,
			BoundingBox.of(
				field.first.toCenterLocation(),
				field.last.toCenterLocation()
			).expand(0.4)
		)

		val playerMarker = PlayerMarker(player, marker, field)
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