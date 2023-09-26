package fyi.pauli.monopoly.api.board.field.persistent

import fyi.pauli.monopoly.api.board.field.Field
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

var Block.field: Field?
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