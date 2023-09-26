package fyi.pauli.monopoly.api.board.field.registry

import fyi.pauli.monopoly.api.board.field.Field
import org.bukkit.util.BoundingBox

interface FieldRegistry {

	val fields: MutableList<Field.Container>

	fun onRegister(container: Field.Container) {}

	fun register(field: Field, area: BoundingBox){
		fields += Field.Container(field, area).also { onRegister(it) }
	}

	fun onUnregister(field: Field) {}

	fun unregister(field: Field) {
		fields.removeIf { it.field == field }.also { onUnregister(field) }
	}

	fun load()

	fun save()

}