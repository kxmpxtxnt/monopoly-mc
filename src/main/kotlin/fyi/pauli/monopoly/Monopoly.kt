package fyi.pauli.monopoly

import fyi.pauli.monopoly.board.field.FieldMarker
import fyi.pauli.monopoly.board.field.FieldRegistry
import fyi.pauli.monopoly.marker.holders.MarkerCache
import fyi.pauli.monopoly.select.selectField
import net.axay.kspigot.main.KSpigot

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
		generateCommand
	}

	override fun shutdown() {

	}
}

val Manager by lazy { Monopoly.INSTANCE }