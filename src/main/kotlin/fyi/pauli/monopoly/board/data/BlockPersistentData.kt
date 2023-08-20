package fyi.pauli.monopoly.board.data

import fyi.pauli.monopoly.board.field.FieldLike
import fyi.pauli.monopoly.board.field.fields.corner.Corner
import fyi.pauli.monopoly.board.field.fields.plant.Plant
import fyi.pauli.monopoly.board.field.fields.special.Special
import fyi.pauli.monopoly.board.field.fields.station.Station
import fyi.pauli.monopoly.board.field.fields.street.Street
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.block.Block
import org.bukkit.persistence.PersistentDataType

val Location.namespacedKey: NamespacedKey
	get() {
		val key = NamespacedKey.fromString((x.toInt() or (z.toInt() shl 4) or (y.toInt() shl 8)).toString(radix = 36))

		checkNotNull(key) {
			"Namespacedkey is null but should not."
		}

		return key
	}

val Block.isFieldBlock: Boolean
	get() = chunk.persistentDataContainer.has(location.namespacedKey)

val all: List<FieldLike> = Street.entries + Station.entries + Plant.entries + Special.entries + Corner.entries

var Block.field: FieldLike?
	get() = all.find {
		it.fieldName == chunk.persistentDataContainer.get(
			location.namespacedKey,
			PersistentDataType.STRING
		)
	}
	set(field) {
		field ?: return
		chunk.persistentDataContainer.set(location.namespacedKey, PersistentDataType.STRING, field.fieldName)
	}
