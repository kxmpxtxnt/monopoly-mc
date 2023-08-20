package fyi.pauli.monopoly.select

import fyi.pauli.monopoly.Monopoly
import fyi.pauli.monopoly.board.data.field
import fyi.pauli.monopoly.board.data.isFieldBlock
import fyi.pauli.monopoly.board.field.marker
import net.axay.kspigot.event.listen
import net.kyori.adventure.bossbar.BossBar
import org.bukkit.FluidCollisionMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent

val streetBossBars: HashMap<Player, BossBar> = HashMap()

private var Player.streetBossBar
	get() = streetBossBars[this]
	set(bossBar) {
		streetBossBars[this]?.removeViewer(this)

		if (bossBar == null) {
			streetBossBars.remove(this)
			return
		}

		streetBossBars[this] = bossBar
		bossBar.addViewer(this)
	}

val selectField = listen<PlayerMoveEvent> { event ->
	if (!event.hasChangedOrientation() && !event.hasChangedBlock()) return@listen

	val block = event.player.getTargetBlockExact(150, FluidCollisionMode.NEVER)

	if (block == null || !block.isSolid || !block.isFieldBlock || block.isEmpty || (block.type == Material.AIR)) {
		event.player.streetBossBar = null
		event.player.marker?.disableMarker()
		return@listen
	}

	val field = Monopoly.INSTANCE.fieldRegistry.fieldByBlock(block) ?: return@listen

	val marker = event.player.marker ?: Monopoly.INSTANCE.fieldMarker.createMarker(event.player, field)

	if (marker.currentField != field || !marker.isVisible) {
		marker.update(field)
	}

	val fieldLike = block.field

	if (fieldLike == null) {
		event.player.streetBossBar = null
		return@listen
	}

	event.player.streetBossBar = BossBar.bossBar(
		fieldLike.coloredName,
		BossBar.MIN_PROGRESS,
		BossBar.Color.WHITE,
		BossBar.Overlay.PROGRESS
	)
}